package org.osj.jumpking.shop.shopBundle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.osj.jumpking.shop.controller.ItemTradeController;
import org.osj.jumpking.shop.controller.ShopManagementController;

import java.util.ArrayList;
import java.util.List;

public class MineralShop implements Listener
{
    private final Inventory inv;
    ItemStack coalBlock = new ItemStack(Material.COAL_BLOCK, 1);
    ItemStack priceCoal = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack ironBlock = new ItemStack(Material.IRON_BLOCK, 1);
    ItemStack priceIron = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack goldBlock = new ItemStack(Material.GOLD_BLOCK, 1);
    ItemStack priceGold = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack redstoneBlock = new ItemStack(Material.REDSTONE_BLOCK, 1);
    ItemStack priceRedstone = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack emeraldBlock = new ItemStack(Material.EMERALD_BLOCK, 1);
    ItemStack priceEmerald = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack lapisBlock = new ItemStack(Material.LAPIS_BLOCK, 1);
    ItemStack priceLapis = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack diamondBlock = new ItemStack(Material.DIAMOND_BLOCK, 1);
    ItemStack priceDiamond = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack amethystBlock = new ItemStack(Material.AMETHYST_BLOCK, 1);
    ItemStack priceAmethyst = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack oxidizedCopperBlock = new ItemStack(Material.OXIDIZED_COPPER, 1);
    ItemStack priceOxidizedCopper = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack netheriteBlock = new ItemStack(Material.NETHERITE_BLOCK, 1);
    ItemStack priceNetherite = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack glowstoneBlock = new ItemStack(Material.GLOWSTONE, 1);
    ItemStack priceGlowstone = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack quartzBlock = new ItemStack(Material.QUARTZ_BLOCK, 1);
    ItemStack priceQuartz = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    int maxSize = 54;

    private void initItemSetting()
    {
        inv.setItem(0, coalBlock);
        priceReload(priceCoal, 50);
        inv.setItem(9, priceCoal);

        inv.setItem(2, ironBlock);
        priceReload(priceIron, 200);
        inv.setItem(11, priceIron);

        inv.setItem(4, goldBlock);
        priceReload(priceGold, 250);
        inv.setItem(13, priceGold);

        inv.setItem(6, redstoneBlock);
        priceReload(priceRedstone, 90);
        inv.setItem(15, priceRedstone);

        inv.setItem(8, emeraldBlock);
        priceReload(priceEmerald, 900);
        inv.setItem(17, priceEmerald);

        inv.setItem(18, lapisBlock);
        priceReload(priceLapis, 220);
        inv.setItem(27, priceLapis);

        inv.setItem(20, diamondBlock);
        priceReload(priceDiamond, 500);
        inv.setItem(29, priceDiamond);

        inv.setItem(22, amethystBlock);
        priceReload(priceAmethyst, 30);
        inv.setItem(31, priceAmethyst);

        inv.setItem(24, oxidizedCopperBlock);
        priceReload(priceOxidizedCopper, 450);
        inv.setItem(33, priceOxidizedCopper);

        inv.setItem(26, netheriteBlock);
        priceReload(priceNetherite, 20000);
        inv.setItem(35, priceNetherite);

        inv.setItem(36, glowstoneBlock);
        priceReload(priceGlowstone, 40);
        inv.setItem(45, priceGlowstone);

        inv.setItem(38, quartzBlock);
        priceReload(priceQuartz, 50);
        inv.setItem(47, priceQuartz);
    }

    private void priceReload(ItemStack priceNameTag, int price)
    {
        ItemMeta meta = priceNameTag.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "[판매가]: " + price);
        lore.add(ChatColor.WHITE + "좌클릭->1개 판매");
        lore.add(ChatColor.WHITE + "쉬프트+좌클릭->64개 판매");
        meta.setLore(lore);
        priceNameTag.setItemMeta(meta);
    }

    public MineralShop()
    {
        this.inv = Bukkit.createInventory(null, maxSize, "MineralShop");
    }

    public void open(Player player)
    {
        initItemSetting();
        player.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        if(!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("MineralShop"))
        {
            return;
        }
        event.setCancelled(true);
        if(event.getClickedInventory() == player.getInventory() || event.getCurrentItem() == null)
        {
            return;
        }
        int num = 1;
        if(event.isLeftClick())
        {
            if(!event.getCurrentItem().getType().equals(Material.NAME_TAG))
            {
                return;
            }
            int price = Integer.parseInt(event.getCurrentItem().getLore().get(0).replace(ChatColor.GOLD + "[판매가]: ", ""));
            if(event.isShiftClick())
            {
                num = 64;
            }
            ItemStack clickedItem = event.getClickedInventory().getItem(event.getSlot() - 9);
            ItemTradeController.SellItemGold(clickedItem, price, num, player);
        }
    }
}