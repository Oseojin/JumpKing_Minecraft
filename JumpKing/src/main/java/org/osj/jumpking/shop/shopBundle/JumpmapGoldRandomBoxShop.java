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
import org.osj.jumpking.CustomItemManager;
import org.osj.jumpking.shop.controller.ItemTradeController;
import org.osj.jumpking.shop.controller.ShopManagementController;

import java.util.ArrayList;
import java.util.List;

public class JumpmapGoldRandomBoxShop implements Listener
{
    private final Inventory inv;
    ItemStack priceGoldRandomBox = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    int maxSize = 45;

    private void initItemSetting()
    {
        if(CustomItemManager.jumpmapGoldRandomBox != null)
        {
            inv.setItem(22, CustomItemManager.jumpmapGoldRandomBox.getItemStack());
        }
        priceReload(priceGoldRandomBox, 10000);
        inv.setItem(31, priceGoldRandomBox);
    }

    private void priceReload(ItemStack priceNameTag, int price)
    {
        ItemMeta meta = priceNameTag.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "[구매가]: " + price);
        lore.add(ChatColor.WHITE + "좌클릭->1개 구매");
        meta.setLore(lore);
        priceNameTag.setItemMeta(meta);
    }

    public JumpmapGoldRandomBoxShop()
    {
        this.inv = Bukkit.createInventory(null, maxSize, "JumpmapGoldRandomShop");
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
        if(!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("JumpmapGoldRandomShop"))
        {
            return;
        }
        event.setCancelled(true);
        if(event.getClickedInventory() == player.getInventory() || event.getCurrentItem() == null)
        {
            return;
        }
        if(event.isLeftClick())
        {
            if(!event.getCurrentItem().getType().equals(Material.NAME_TAG))
            {
                return;
            }
            int price = Integer.parseInt(event.getCurrentItem().getLore().get(0).replace(ChatColor.GOLD + "[구매가]: ", ""));

            ItemTradeController.PurchaseItemGold(CustomItemManager.jumpmapGoldRandomBox.getItemStack(), price, player);
        }
    }
}
