package org.osj.jumpking.shop.shopBundle;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.osj.jumpking.CustomItemManager;
import org.osj.jumpking.jumpmap.ShoesStatManager;
import org.osj.jumpking.shop.controller.ItemTradeController;
import org.osj.jumpking.shop.controller.ShopManagementController;
import org.osj.jumpking.user.management.controller.UserManagementController;
import org.osj.jumpking.user.management.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShoesUpgradeShop implements Listener
{
    private final Inventory inv;
    private static ItemStack upgradeButton_noTicket = ShopManagementController.buildGeneralItem(Material.END_CRYSTAL, ChatColor.RED + "[일반 강화]", null, 0);
    private static ItemStack upgradeButton_onTicket = ShopManagementController.buildGeneralItem(Material.END_CRYSTAL, ChatColor.YELLOW + "[방지권 사용 강화]", null, 0);
    private static ItemStack greenGlass = ShopManagementController.buildGeneralItem(Material.GREEN_STAINED_GLASS_PANE, "", null, 0);
    private static ItemStack grayGlass = ShopManagementController.buildGeneralItem(Material.GRAY_STAINED_GLASS_PANE, "", null, 0);
    int maxSize = 54;

    private static String[][] placeArray =
            {
                    {"e", "e", "e", "e", "e", "e", "e", "e", "e"},
                    {"g", "g", "g", "e", "e", "e", "g", "g", "g"},
                    {"g", "shoes", "g", "g", "g", "g", "g", "result", "g"},
                    {"g", "g", "g", "e", "e", "e", "g", "g", "g"},
                    {"e", "e", "e", "button1", "e", "button2", "e", "e", "e"},
                    {"e", "e", "e", "e", "e", "e", "e", "e", "e"}
            };

    private void initItemSetting()
    {
        priceReload(upgradeButton_noTicket, 0, 0, 0, 0, false);
        priceReload(upgradeButton_onTicket, 0, 0, 0, 0, true);
        int colLen = placeArray.length;
        int rowLen = placeArray[0].length;
        for(int column = 0; column < colLen; column++)
        {
            for(int row = 0; row < rowLen; row++)
            {
                ItemStack stack;
                switch (placeArray[column][row])
                {
                    case "g":
                        stack = greenGlass;
                        break;
                    case "shoes":
                    case "result":
                        continue;
                    case "button1":
                        stack = upgradeButton_noTicket;
                        break;
                    case "button2":
                        stack = upgradeButton_onTicket;
                        break;
                    default:
                        stack = grayGlass;
                }

                inv.setItem(column * rowLen + row, stack);
            }
        }
    }

    private void priceReload(ItemStack targetItem, int needStoneNum, int gold, int jumpingCoin, int prob, boolean isTicket)
    {
        ItemMeta meta = targetItem.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BOLD + "[강화필요재료]");
        lore.add(ChatColor.RED + "[강화석] " + needStoneNum + "개");
        lore.add(ChatColor.GOLD + "[골드] " + gold + "G");
        lore.add(ChatColor.GREEN + "[점프코인] " + jumpingCoin + "JC");
        lore.add(ChatColor.DARK_PURPLE + "[강화 확률] " + prob + "%");
        if(isTicket)
        {
            lore.add(ChatColor.YELLOW + "[단계 하락 방지권] 1개");
            lore.add(ChatColor.WHITE + "좌클릭시 방지권을 소모해 강화");
        }
        else
        {
            lore.add(ChatColor.WHITE + "좌클릭시 방지권을 소모하지 않고 강화");
        }
        meta.setLore(lore);
        targetItem.setItemMeta(meta);
    }

    public ShoesUpgradeShop()
    {
        this.inv = Bukkit.createInventory(null, maxSize, "ShoesUpgradeShop");
    }

    public void open(Player player)
    {
        initItemSetting();
        player.openInventory(inv);
    }

    @EventHandler
    public void closeInv(InventoryCloseEvent event)
    {
        if(!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("ShoesUpgradeShop"))
        {
            return;
        }

        if(event.getInventory().getItem(19) != null)
        {
            event.getPlayer().getInventory().addItem(event.getInventory().getItem(19));
        }
        if(event.getInventory().getItem(25) != null)
        {
            event.getPlayer().getInventory().addItem(event.getInventory().getItem(25));
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        if(!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("ShoesUpgradeShop"))
        {
            return;
        }
        event.setCancelled(true);

        if(!event.isLeftClick() || event.getCurrentItem() == null)
        {
            return;
        }

        if(event.getClickedInventory() == player.getInventory())
        {
            CustomStack customStack = CustomStack.byItemStack(event.getCurrentItem());
            // 커스텀 아이템인지 확인 || 신발 권한을 가지고 있지 않으면 반환
            if(customStack != null && customStack.getPermission().equals("ia.jumpking:jumping_shoes"))
            {
                event.getInventory().setItem(19, customStack.getItemStack());
                player.getInventory().remove(customStack.getItemStack());

                int lv = ShoesStatManager.getShoesLv(customStack.getItemStack());
                List<Integer> resourceList = getUpgradeStat(lv);

                player.sendMessage("신발: " + resourceList.get(0) + " " + resourceList.get(1) + " " + resourceList.get(2) + " " + resourceList.get(3));

                priceReload(upgradeButton_noTicket, resourceList.get(0), resourceList.get(1), resourceList.get(2), resourceList.get(3), false);
                priceReload(upgradeButton_onTicket, resourceList.get(0), resourceList.get(1), resourceList.get(2), resourceList.get(3), true);

                event.getInventory().setItem(39, upgradeButton_noTicket);
                event.getInventory().setItem(41, upgradeButton_onTicket);
            }
        }
        else
        {
            CustomStack customStack = CustomStack.byItemStack(event.getCurrentItem());
            // 커스텀 아이템인지 확인 || 신발 권한을 가지고 있지 않으면 반환
            if(customStack != null && customStack.getPermission().equals("ia.jumpking:jumping_shoes"))
            {
                event.getWhoClicked().getInventory().addItem(customStack.getItemStack());
                event.getClickedInventory().remove(customStack.getItemStack());
                return;
            }

            if(!event.getCurrentItem().getType().equals(Material.END_CRYSTAL))
            {
                return;
            }

            if(event.getClickedInventory().getItem(19) == null)
            {
                player.sendMessage(ChatColor.RED + "신발을 왼쪽칸에 넣어주세요.");
                return;
            }

            CustomStack playerShoes = CustomStack.byItemStack(event.getClickedInventory().getItem(19));

            ItemStack button = event.getCurrentItem();

            boolean useTicket = button.getLore().size() == 7;

            UpgradeShoes(playerShoes.getItemStack(), useTicket, player, event.getClickedInventory());
        }
    }

    private List<Integer> getUpgradeStat(int lv)
    {
        List<Integer> resourceList = new ArrayList<>();
        switch (lv)
        {
            case 0:
                resourceList.add(1);
                resourceList.add(2500);
                resourceList.add(0);
                resourceList.add(100);
                break;
            case 1:
                resourceList.add(2);
                resourceList.add(5000);
                resourceList.add(0);
                resourceList.add(90);
                break;
            case 2:
                resourceList.add(4);
                resourceList.add(7500);
                resourceList.add(500);
                resourceList.add(80);
                break;
            case 3:
                resourceList.add(8);
                resourceList.add(10000);
                resourceList.add(1000);
                resourceList.add(70);
                break;
            case 4:
                resourceList.add(16);
                resourceList.add(20000);
                resourceList.add(1000);
                resourceList.add(60);
                break;
            case 5:
                resourceList.add(24);
                resourceList.add(30000);
                resourceList.add(3000);
                resourceList.add(50);
                break;
            case 6:
                resourceList.add(32);
                resourceList.add(40000);
                resourceList.add(4000);
                resourceList.add(40);
                break;
            case 7:
                resourceList.add(40);
                resourceList.add(50000);
                resourceList.add(5000);
                resourceList.add(30);
                break;
            case 8:
                resourceList.add(52);
                resourceList.add(75000);
                resourceList.add(7500);
                resourceList.add(20);
                break;
            case 9:
                resourceList.add(64);
                resourceList.add(100000);
                resourceList.add(10000);
                resourceList.add(10);
                break;
            default:
                resourceList.add(0);
                resourceList.add(0);
                resourceList.add(0);
        }

        return resourceList;
    }

    public void UpgradeShoes(ItemStack playerShoes, boolean useTicket, Player player, Inventory eventInv)
    {
        User user = UserManagementController.getUserManager().getUserData(player);
        int lv = ShoesStatManager.getShoesLv(playerShoes);
        List<Integer> resourceList = getUpgradeStat(lv);
        int needStoneNum = resourceList.get(0);
        int gold = resourceList.get(1);
        int jumpingCoin = resourceList.get(2);
        int prob = resourceList.get(3);

        if(user.getGold() < gold || user.getJumpingCoin() < jumpingCoin || !player.getInventory().containsAtLeast(CustomItemManager.reinforceStone.getItemStack(), needStoneNum))
        {
            player.sendMessage(ChatColor.RED + "재료가 부족합니다.");
            return;
        }
        if(useTicket && !player.getInventory().containsAtLeast(CustomItemManager.downPreventTicket.getItemStack(), 1))
        {
            player.sendMessage(ChatColor.RED + "단계 하락 방지권이 없습니다.");
            return;
        }

        int count = needStoneNum;
        for(int i = 0; i < player.getInventory().getSize(); i++)
        {
            ItemStack currItem = player.getInventory().getItem(i);
            if(currItem == null)
            {
                continue;
            }
            if(currItem.isSimilar(CustomItemManager.reinforceStone.getItemStack()))
            {
                if(currItem.getAmount() >= count)
                {
                    currItem.setAmount(currItem.getAmount() - count);
                    break;
                }
                else
                {
                    count -= currItem.getAmount();
                    currItem.setAmount(0);
                }
            }
        }
        user.setGold(user.getGold() - gold);
        user.setJumpingCoin(user.getJumpingCoin() - jumpingCoin);

        Random random = new Random();
        int upgradeRandom = random.nextInt(100) + 1;
        if(upgradeRandom <= prob) // 강화성공
        {
            eventInv.remove(playerShoes);
            ItemMeta shoesMeta = playerShoes.getItemMeta();
            shoesMeta.setDisplayName(ChatColor.GREEN + "점핑슈즈[+" + (lv+1) + "]");
            playerShoes.setItemMeta(shoesMeta);
            eventInv.setItem(25, playerShoes);
            player.sendMessage(ChatColor.AQUA + "강화가 성공하였습니다! " + ChatColor.RED + "(-" + needStoneNum + " 강화석)" + ChatColor.GOLD + "(-" + gold + "G)" + ChatColor.GREEN + "(-" + jumpingCoin + "JC)");
        }
        else // 강화 실패
        {
            player.sendMessage(ChatColor.RED + "강화가 실패하였습니다.. " + ChatColor.RED + "(-" + needStoneNum + " 강화석)" + ChatColor.GOLD + "(-" + gold + "G)" + ChatColor.GREEN + "(-" + jumpingCoin + "JC)");
            if(useTicket)
            {
                for(int i = 0; i < player.getInventory().getSize(); i++)
                {
                    ItemStack currItem = player.getInventory().getItem(i);
                    if (currItem == null)
                    {
                        continue;
                    }
                    if (currItem.isSimilar(CustomItemManager.downPreventTicket.getItemStack()))
                    {
                        currItem.setAmount(currItem.getAmount()-1);
                    }
                }

                eventInv.remove(playerShoes);
                eventInv.setItem(25, playerShoes);
                player.sendMessage(ChatColor.AQUA + "단계 하락 방지권을 사용하여 단계가 하락하지 않습니다.");
            }
            else if(lv >= 1)
            {
                eventInv.remove(playerShoes);
                ItemMeta shoesMeta = playerShoes.getItemMeta();
                shoesMeta.setDisplayName(ChatColor.GREEN + "점핑슈즈[+" + (lv-1) + "]");
                playerShoes.setItemMeta(shoesMeta);
                eventInv.setItem(25, playerShoes);
                player.sendMessage(ChatColor.RED + "강화가 실패하여 강화 단계가 1 하락합니다.");
            }
        }

    }
}
