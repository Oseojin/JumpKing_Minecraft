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

public class AnimalShop implements Listener
{
    private final Inventory inv;
    ItemStack whiteWool = new ItemStack(Material.WHITE_WOOL, 1);
    ItemStack priceWhiteWool = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack leather = new ItemStack(Material.LEATHER, 1);
    ItemStack priceLeather = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack beef = new ItemStack(Material.BEEF, 1);
    ItemStack priceBeef = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack rabbit = new ItemStack(Material.RABBIT, 1);
    ItemStack priceRabbit = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack porkchop = new ItemStack(Material.PORKCHOP, 1);
    ItemStack priceProkchop = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack mutton = new ItemStack(Material.MUTTON, 1);
    ItemStack priceMutton = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack chicken = new ItemStack(Material.CHICKEN, 1);
    ItemStack priceChicken = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack feather = new ItemStack(Material.FEATHER, 1);
    ItemStack priceFeather = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack egg = new ItemStack(Material.EGG, 1);
    ItemStack priceEgg = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack rabbitFoot = new ItemStack(Material.RABBIT_FOOT, 1);
    ItemStack priceRabbitFoot = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack rabbitHide = new ItemStack(Material.RABBIT_HIDE, 1);
    ItemStack priceRabbitHide = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack inkSac = new ItemStack(Material.INK_SAC, 1);
    ItemStack priceInkSac = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack glowInkSac = new ItemStack(Material.GLOW_INK_SAC, 1);
    ItemStack priceGlowInkSac = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack cod = new ItemStack(Material.COD, 1);
    ItemStack priceCod = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack salmon = new ItemStack(Material.SALMON, 1);
    ItemStack priceSalmon = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack pufferFish = new ItemStack(Material.PUFFERFISH, 1);
    ItemStack pricePufferFish = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    ItemStack tropicalFish = new ItemStack(Material.TROPICAL_FISH, 1);
    ItemStack priceTropicalFish = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    int maxSize = 54;

    private void initItemSetting()
    {
        inv.setItem(0, whiteWool);
        priceReload(priceWhiteWool, 30);
        inv.setItem(9, priceWhiteWool);

        inv.setItem(2, leather);
        priceReload(priceLeather, 45);
        inv.setItem(11, priceLeather);

        inv.setItem(4, beef);
        priceReload(priceBeef, 45);
        inv.setItem(13, priceBeef);

        inv.setItem(6, rabbit);
        priceReload(priceRabbit, 45);
        inv.setItem(15, priceRabbit);

        inv.setItem(8, porkchop);
        priceReload(priceProkchop, 45);
        inv.setItem(17, priceProkchop);

        inv.setItem(18, mutton);
        priceReload(priceMutton, 45);
        inv.setItem(27, priceMutton);

        inv.setItem(20, chicken);
        priceReload(priceChicken, 45);
        inv.setItem(29, priceChicken);

        inv.setItem(22, feather);
        priceReload(priceFeather, 30);
        inv.setItem(31, priceFeather);

        inv.setItem(24, egg);
        priceReload(priceEgg, 15);
        inv.setItem(33, priceEgg);

        inv.setItem(26, rabbitFoot);
        priceReload(priceRabbitFoot, 105);
        inv.setItem(35, priceRabbitFoot);

        inv.setItem(36, rabbitHide);
        priceReload(priceRabbitHide, 30);
        inv.setItem(45, priceRabbitHide);

        inv.setItem(38, inkSac);
        priceReload(priceInkSac, 45);
        inv.setItem(47, priceInkSac);

        inv.setItem(40, glowInkSac);
        priceReload(priceGlowInkSac, 75);
        inv.setItem(49, priceGlowInkSac);

        inv.setItem(41, cod);
        priceReload(priceCod, 30);
        inv.setItem(50, priceCod);

        inv.setItem(42, salmon);
        priceReload(priceSalmon, 35);
        inv.setItem(51, priceSalmon);

        inv.setItem(43, pufferFish);
        priceReload(pricePufferFish, 70);
        inv.setItem(52, pricePufferFish);

        inv.setItem(44, tropicalFish);
        priceReload(priceTropicalFish, 100);
        inv.setItem(53, priceTropicalFish);
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

    public AnimalShop()
    {
        this.inv = Bukkit.createInventory(null, maxSize, "AnimalShop");
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
        if(!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("AnimalShop"))
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
