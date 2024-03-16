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

public class MonsterShop implements Listener
{
    private final Inventory inv;
    ItemStack rottenFlesh = new ItemStack(Material.ROTTEN_FLESH, 1);
    ItemStack priceRottenFlesh = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack bone = new ItemStack(Material.BONE, 1);
    ItemStack priceBone = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack arrow = new ItemStack(Material.ARROW, 1);
    ItemStack priceArrow = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack gunPowder = new ItemStack(Material.GUNPOWDER, 1);
    ItemStack priceGunPowder = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack spiderEye = new ItemStack(Material.SPIDER_EYE, 1);
    ItemStack priceSpiderEye = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack string = new ItemStack(Material.STRING, 1);
    ItemStack priceString = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack enderPearl = new ItemStack(Material.ENDER_PEARL, 1);
    ItemStack priceEnderPearl = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack slimeBall = new ItemStack(Material.SLIME_BALL, 1);
    ItemStack priceSlimeBall = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack blazeRod = new ItemStack(Material.BLAZE_ROD, 1);
    ItemStack priceBlazeRod = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack shulkerShell = new ItemStack(Material.SHULKER_SHELL, 1);
    ItemStack priceShulkerShell = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack phantomMembrane = new ItemStack(Material.PHANTOM_MEMBRANE, 1);
    ItemStack pricePhantomMembrane = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack netherStar = new ItemStack(Material.NETHER_STAR, 1);
    ItemStack priceNetherStar = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    int maxSize = 54;

    private void initItemSetting()
    {
        inv.setItem(0, rottenFlesh);
        priceReload(priceRottenFlesh, 30);
        inv.setItem(9, priceRottenFlesh);

        inv.setItem(2, bone);
        priceReload(priceBone, 40);
        inv.setItem(11, priceBone);

        inv.setItem(4, arrow);
        priceReload(priceArrow, 7);
        inv.setItem(13, priceArrow);

        inv.setItem(6, gunPowder);
        priceReload(priceGunPowder, 70);
        inv.setItem(15, priceGunPowder);

        inv.setItem(8, spiderEye);
        priceReload(priceSpiderEye, 50);
        inv.setItem(17, priceSpiderEye);

        inv.setItem(18, string);
        priceReload(priceString, 10);
        inv.setItem(27, priceString);

        inv.setItem(20, enderPearl);
        priceReload(priceEnderPearl, 40);
        inv.setItem(29, priceEnderPearl);

        inv.setItem(22, slimeBall);
        priceReload(priceSlimeBall, 20);
        inv.setItem(31, priceSlimeBall);

        inv.setItem(24, blazeRod);
        priceReload(priceBlazeRod, 70);
        inv.setItem(33, priceBlazeRod);

        inv.setItem(26, shulkerShell);
        priceReload(priceShulkerShell, 100);
        inv.setItem(35, priceShulkerShell);

        inv.setItem(36, phantomMembrane);
        priceReload(pricePhantomMembrane, 50);
        inv.setItem(45, pricePhantomMembrane);

        inv.setItem(38, netherStar);
        priceReload(priceNetherStar, 5000);
        inv.setItem(47, priceNetherStar);
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

    public MonsterShop()
    {
        this.inv = Bukkit.createInventory(null, maxSize, "MonsterShop");
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
        if(!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("MonsterShop"))
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
