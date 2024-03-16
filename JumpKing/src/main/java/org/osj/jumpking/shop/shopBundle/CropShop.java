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

public class CropShop implements Listener
{
    private final Inventory inv;
    ItemStack wheat = new ItemStack(Material.WHEAT, 1);
    ItemStack priceWheat = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack apple = new ItemStack(Material.APPLE, 1);
    ItemStack priceApple = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack melon = new ItemStack(Material.MELON, 1);
    ItemStack priceMelon = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack carrot = new ItemStack(Material.CARROT, 1);
    ItemStack priceCarrot = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack potato = new ItemStack(Material.POTATO, 1);
    ItemStack pricePotato = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack poisonousPotato = new ItemStack(Material.POISONOUS_POTATO, 1);
    ItemStack pricePoisonousPotato = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack beetRoot = new ItemStack(Material.BEETROOT, 1);
    ItemStack priceBeetRoot = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack pumpkin = new ItemStack(Material.PUMPKIN, 1);
    ItemStack pricePumpkin = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    int maxSize = 54;

    private void initItemSetting()
    {
        inv.setItem(0, wheat);
        priceReload(priceWheat, 17);
        inv.setItem(9, priceWheat);

        inv.setItem(2, apple);
        priceReload(priceApple, 30);
        inv.setItem(11, priceApple);

        inv.setItem(4, melon);
        priceReload(priceMelon, 7);
        inv.setItem(13, priceMelon);

        inv.setItem(6, carrot);
        priceReload(priceCarrot, 6);
        inv.setItem(15, priceCarrot);

        inv.setItem(8, potato);
        priceReload(pricePotato, 6);
        inv.setItem(17, pricePotato);

        inv.setItem(18, poisonousPotato);
        priceReload(pricePoisonousPotato, 40);
        inv.setItem(27, pricePoisonousPotato);

        inv.setItem(20, beetRoot);
        priceReload(priceBeetRoot, 25);
        inv.setItem(29, priceBeetRoot);

        inv.setItem(22, pumpkin);
        priceReload(pricePumpkin, 30);
        inv.setItem(31, pricePumpkin);
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

    public CropShop()
    {
        this.inv = Bukkit.createInventory(null, maxSize, "CropShop");
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
        if(!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("CropShop"))
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
