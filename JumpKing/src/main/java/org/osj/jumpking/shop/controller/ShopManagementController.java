package org.osj.jumpking.shop.controller;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.osj.jumpking.JumpKing;
import org.osj.jumpking.shop.*;
import org.osj.jumpking.shop.controller.commands.ShopOpenTestCommand;
import org.osj.jumpking.shop.shopBundle.*;

import java.util.Arrays;

public class ShopManagementController
{
    private final JumpKing serverInstance;

    public static ItemStack buildGeneralItem(Material type, String displayName, Enchantment enchantment, int enchantLevel, String... lore)
    {
        ItemStack itemStack = new ItemStack(type);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        if(enchantment != null)
        {
            itemMeta.addEnchant(enchantment, enchantLevel, true);
        }
        for(int i = 0; i < lore.length; i++)
        {
            lore[i] = ChatColor.translateAlternateColorCodes('&', lore[i]);
        }
        itemMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ShopManagementController()
    {
        this.serverInstance = JumpKing.getServerInstance();

        registerEvents();
        registerCommands();
    }

    private void registerEvents()
    {
        serverInstance.getServer().getPluginManager().registerEvents(new MineralShop(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new CropShop(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new MonsterShop(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new AnimalShop(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new WildGoldRandomBoxShop(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new JumpmapGoldRandomBoxShop(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new WildJumpingRandomBoxShop(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new JumpmapJumpingRandomBoxShop(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new BlockClickEvent(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new ShoesUpgradeShop(), serverInstance);
    }
    private void registerCommands()
    {
        serverInstance.getServer().getPluginCommand("shopTest").setExecutor(new ShopOpenTestCommand());
    }
}
