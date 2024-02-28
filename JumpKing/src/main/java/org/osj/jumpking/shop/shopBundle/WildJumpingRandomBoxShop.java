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

public class WildJumpingRandomBoxShop implements Listener
{
    private final Inventory inv;
    ItemStack priceJumpingRandomBox = ShopManagementController.buildGeneralItem(Material.NAME_TAG, ChatColor.WHITE + "가격표", null, 0);
    int maxSize = 45;

    private void initItemSetting()
    {
        if(CustomItemManager.wildJumpingRandomBox != null)
        {
            inv.setItem(22, CustomItemManager.wildJumpingRandomBox.getItemStack());
        }
        priceReload(priceJumpingRandomBox, 500);
        inv.setItem(31, priceJumpingRandomBox);
    }

    private void priceReload(ItemStack priceNameTag, int price)
    {
        ItemMeta meta = priceNameTag.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "[구매가]: " + price);
        lore.add(ChatColor.WHITE + "좌클릭->1개 구매");
        meta.setLore(lore);
        priceNameTag.setItemMeta(meta);
    }

    public WildJumpingRandomBoxShop()
    {
        this.inv = Bukkit.createInventory(null, maxSize, "WildJumpingRandomShop");
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
        if(!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("WildJumpingRandomShop"))
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
            int price = Integer.parseInt(event.getCurrentItem().getLore().get(0).replace(ChatColor.GREEN + "[구매가]: ", ""));

            ItemTradeController.PurchaseItemCoin(CustomItemManager.wildJumpingRandomBox.getItemStack(), price, player);
        }
    }
}
