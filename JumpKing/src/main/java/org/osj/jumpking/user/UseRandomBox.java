package org.osj.jumpking.user;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.osj.jumpking.CustomItemManager;
import org.osj.jumpking.JumpKing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class UseRandomBox implements Listener
{
    private HashMap<Player, ItemStack> randomItemMap = new HashMap<>();
    @EventHandler
    public void useRandomBox(PlayerInteractEvent event)
    {
        ItemStack playerHand = event.getPlayer().getInventory().getItemInMainHand();
        if (event.getAction().isRightClick() && playerHand != null)
        {
            if (playerHand.isSimilar(CustomItemManager.wildGoldRandomBox.getItemStack()))
            {
                playerHand.setAmount(playerHand.getAmount() - 1);
                randomItemMap.put(event.getPlayer(), wildGoldRandomBox());
            }
            else if (playerHand.isSimilar(CustomItemManager.wildJumpingRandomBox.getItemStack()))
            {
                playerHand.setAmount(playerHand.getAmount() - 1);
                randomItemMap.put(event.getPlayer(), wildJumpingRandomBox());
            }
            else if (playerHand.isSimilar(CustomItemManager.jumpmapGoldRandomBox.getItemStack()))
            {
                playerHand.setAmount(playerHand.getAmount() - 1);
                randomItemMap.put(event.getPlayer(), jumpmapGoldRandomBox());
            }
            else if (playerHand.isSimilar(CustomItemManager.jumpmapJumpingRandomBox.getItemStack()))
            {
                playerHand.setAmount(playerHand.getAmount() - 1);
                randomItemMap.put(event.getPlayer(), jumpmapJumpingRandomBox());
            }

            if(randomItemMap.containsKey(event.getPlayer()))
            {
                new BukkitRunnable()
                {
                    @Override
                    public void run()
                    {
                        event.getPlayer().getInventory().addItem(randomItemMap.get(event.getPlayer()));
                        randomItemMap.remove(event.getPlayer());
                    }
                }.runTaskLater(JumpKing.getServerInstance(), 1L);
            }
        }
    }

    private ItemStack wildGoldRandomBox()
    {
        ItemStack randomItem;
        Random random = new Random();
        int n = random.nextInt(1000) + 1;

        if(n <= 10)
        {
            randomItem = CustomItemManager.landPurchaseTicket.getItemStack();
        }
        else if(n <= 20)
        {
            randomItem = CustomItemManager.userInviteTicket.getItemStack();
        }
        else if(n <= 70)
        {
            randomItem = CustomItemManager.inventorySaveTicket.getItemStack();
        }
        else if(n <= 160)
        {
            randomItem = CustomItemManager.enchantRandomTicket.getItemStack();
        }
        else if(n <= 360)
        {
            randomItem = CustomItemManager.villageReturnTicket.getItemStack();
        }
        else if(n <= 610)
        {
            randomItem = new ItemStack(Material.FIREWORK_ROCKET, 1);
        }
        else
        {
            randomItem = CustomItemManager.jumpingCookie.getItemStack();
            randomItem.setAmount(1);
        }

        return randomItem;
    }
    private ItemStack wildJumpingRandomBox()
    {
        ItemStack randomItem;
        Random random = new Random();
        int n = random.nextInt(1000) + 1;

        if(n <= 10)
        {
            randomItem = new ItemStack(Material.NETHERITE_INGOT, 1);
        }
        else if(n <= 30)
        {
            randomItem = CustomItemManager.userInviteTicket.getItemStack();
        }
        else if(n <= 50)
        {
            randomItem = CustomItemManager.landPurchaseTicket.getItemStack();
        }
        else if(n <= 130)
        {
            randomItem = CustomItemManager.inventorySaveTicket.getItemStack();
        }
        else if(n <= 220)
        {
            randomItem = CustomItemManager.goldPouch.getItemStack();
        }
        else if(n <= 370)
        {
            randomItem = CustomItemManager.enchantRandomTicket.getItemStack();
        }
        else if(n <= 570)
        {
            randomItem = CustomItemManager.villageReturnTicket.getItemStack();
        }
        else if(n <= 770)
        {
            randomItem = new ItemStack(Material.FIREWORK_ROCKET, 5);
        }
        else
        {
            randomItem = CustomItemManager.jumpingCookie.getItemStack();
            randomItem.setAmount(5);
        }

        return randomItem;
    }
    private ItemStack jumpmapGoldRandomBox()
    {
        ItemStack randomItem;
        Random random = new Random();
        int n = random.nextInt(1000) + 1;

        if(n <= 10)
        {
            randomItem = new ItemStack(Material.SNOWBALL, 1);
            ItemMeta meta = randomItem.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + "용수철 눈덩이");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.AQUA + "플레이어에게 맞추면 해당 플레이어를 하늘 높이 띄운다.");
            meta.setLore(lore);
            randomItem.setItemMeta(meta);
        }
        else if(n <= 20)
        {
            randomItem = CustomItemManager.userInviteTicket.getItemStack();
        }
        else if(n <= 30)
        {
            randomItem = CustomItemManager.downPreventTicket.getItemStack();
        }
        /*else if(n <= 7)
        {
            randomItem = CustomItemManager.shiningStone.getItemStack();
        }*/
        else
        {
            randomItem = CustomItemManager.reinforceStone.getItemStack();
        }

        return randomItem;
    }
    private ItemStack jumpmapJumpingRandomBox()
    {
        ItemStack randomItem;
        Random random = new Random();
        int n = random.nextInt(1000) + 1;

        if(n <= 25)
        {
            randomItem = CustomItemManager.userInviteTicket.getItemStack();
        }
        else if(n <= 75)
        {
            randomItem = new ItemStack(Material.SNOWBALL, 1);
            ItemMeta meta = randomItem.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + "용수철 눈덩이");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.AQUA + "플레이어에게 맞추면 해당 플레이어를 하늘 높이 띄운다.");
            meta.setLore(lore);
            randomItem.setItemMeta(meta);
        }
        else if(n <= 125)
        {
            randomItem = CustomItemManager.downPreventTicket.getItemStack();
        }
        /*else if(n <= 25)
        {
            randomItem = CustomItemManager.shiningStone.getItemStack();
        }*/
        else
        {
            randomItem = CustomItemManager.reinforceStone.getItemStack();
        }

        return randomItem;
    }
}
