package org.osj.jumpking.user;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.osj.jumpking.CustomItemManager;

import java.util.HashMap;
import java.util.Random;

public class UseEnchantRandom implements Listener
{
    HashMap<Enchantment, Integer> enchantBookMap = new HashMap<>()
    {{
        put(Enchantment.MENDING, 1);
        put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        put(Enchantment.DAMAGE_ALL, 5);
        put(Enchantment.ARROW_INFINITE, 1);
        put(Enchantment.DIG_SPEED, 5);
        put(Enchantment.LURE, 3);
        put(Enchantment.LUCK, 3);
        put(Enchantment.LOOT_BONUS_BLOCKS, 3);
        put(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        put(Enchantment.DAMAGE_ALL, 4);
        put(Enchantment.DIG_SPEED, 4);
        put(Enchantment.LURE, 2);
        put(Enchantment.LUCK, 2);
        put(Enchantment.LOOT_BONUS_BLOCKS, 2);
        put(Enchantment.PIERCING, 5);
        put(Enchantment.DAMAGE_ARTHROPODS, 5);
        put(Enchantment.PROTECTION_FALL, 4);
        put(Enchantment.LOOT_BONUS_MOBS, 3);
        put(Enchantment.ARROW_DAMAGE, 5);
        put(Enchantment.DURABILITY, 3);
        put(Enchantment.LOYALTY, 3);
        put(Enchantment.DAMAGE_UNDEAD, 5);
        put(Enchantment.CHANNELING, 1);
        put(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        put(Enchantment.DAMAGE_ALL, 3);
        put(Enchantment.DIG_SPEED, 3);
        put(Enchantment.LURE, 1);
        put(Enchantment.LUCK, 1);
        put(Enchantment.LOOT_BONUS_BLOCKS, 1);
        put(Enchantment.IMPALING, 4);
        put(Enchantment.DAMAGE_ARTHROPODS, 4);
        put(Enchantment.PROTECTION_FALL, 3);
        put(Enchantment.LOOT_BONUS_MOBS, 2);
        put(Enchantment.ARROW_DAMAGE, 4);
        put(Enchantment.DURABILITY, 2);
        put(Enchantment.LOYALTY, 2);
        put(Enchantment.DAMAGE_UNDEAD, 4);
        put(Enchantment.PROTECTION_FIRE, 4);
        put(Enchantment.PROTECTION_EXPLOSIONS, 4);
        put(Enchantment.PROTECTION_PROJECTILE, 4);
        put(Enchantment.KNOCKBACK, 2);
        put(Enchantment.ARROW_FIRE, 1);
        put(Enchantment.SWIFT_SNEAK, 3);
        put(Enchantment.MULTISHOT, 1);
        put(Enchantment.THORNS, 3);
        put(Enchantment.RIPTIDE, 3);
        put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        put(Enchantment.DAMAGE_ALL, 2);
        put(Enchantment.DIG_SPEED, 2);
        put(Enchantment.PIERCING, 3);
        put(Enchantment.DAMAGE_ARTHROPODS, 3);
        put(Enchantment.PROTECTION_FALL, 2);
        put(Enchantment.LOOT_BONUS_MOBS, 1);
        put(Enchantment.ARROW_DAMAGE, 3);
        put(Enchantment.DURABILITY, 1);
        put(Enchantment.LOYALTY, 1);
        put(Enchantment.DAMAGE_UNDEAD, 3);
        put(Enchantment.PROTECTION_FIRE, 3);
        put(Enchantment.PROTECTION_EXPLOSIONS, 3);
        put(Enchantment.PROTECTION_PROJECTILE, 3);
        put(Enchantment.KNOCKBACK, 1);
        put(Enchantment.SWIFT_SNEAK, 2);
        put(Enchantment.THORNS, 2);
        put(Enchantment.RIPTIDE, 2);
        put(Enchantment.SOUL_SPEED, 3);
        put(Enchantment.WATER_WORKER, 1);
        put(Enchantment.FROST_WALKER, 2);
        put(Enchantment.PIERCING, 4);
        put(Enchantment.OXYGEN, 3);
        put(Enchantment.ARROW_KNOCKBACK, 2);
        put(Enchantment.SWEEPING_EDGE, 3);
        put(Enchantment.DEPTH_STRIDER, 3);
        put(Enchantment.QUICK_CHARGE, 3);
        put(Enchantment.DAMAGE_ALL, 1);
        put(Enchantment.DIG_SPEED, 1);
        put(Enchantment.IMPALING, 2);
        put(Enchantment.DAMAGE_ARTHROPODS, 2);
        put(Enchantment.PROTECTION_FALL, 1);
        put(Enchantment.ARROW_DAMAGE, 2);
        put(Enchantment.DAMAGE_UNDEAD, 2);
        put(Enchantment.PROTECTION_FIRE, 2);
        put(Enchantment.PROTECTION_EXPLOSIONS, 2);
        put(Enchantment.PROTECTION_PROJECTILE, 2);
        put(Enchantment.SWIFT_SNEAK, 1);
        put(Enchantment.THORNS, 1);
        put(Enchantment.RIPTIDE, 1);
        put(Enchantment.SOUL_SPEED, 2);
        put(Enchantment.FROST_WALKER, 1);
        put(Enchantment.PIERCING, 3);
        put(Enchantment.OXYGEN, 2);
        put(Enchantment.ARROW_KNOCKBACK, 1);
        put(Enchantment.SWEEPING_EDGE, 2);
        put(Enchantment.DEPTH_STRIDER, 2);
        put(Enchantment.QUICK_CHARGE, 2);
        put(Enchantment.SILK_TOUCH, 1);
        put(Enchantment.FIRE_ASPECT, 2);
        put(Enchantment.IMPALING, 1);
        put(Enchantment.DAMAGE_ARTHROPODS, 1);
        put(Enchantment.ARROW_DAMAGE, 1);
        put(Enchantment.DAMAGE_UNDEAD, 1);
        put(Enchantment.PROTECTION_FIRE, 1);
        put(Enchantment.PROTECTION_EXPLOSIONS, 1);
        put(Enchantment.PROTECTION_PROJECTILE, 1);
        put(Enchantment.SOUL_SPEED, 1);
        put(Enchantment.PIERCING, 1);
        put(Enchantment.PIERCING, 2);
        put(Enchantment.OXYGEN, 1);
        put(Enchantment.SWEEPING_EDGE, 1);
        put(Enchantment.DEPTH_STRIDER, 1);
        put(Enchantment.QUICK_CHARGE, 1);
        put(Enchantment.FIRE_ASPECT, 1);
        put(Enchantment.VANISHING_CURSE, 1);
        put(Enchantment.BINDING_CURSE, 1);
    }};

    @EventHandler
    public void useEnchantRandomTicket(PlayerInteractEvent event)
    {
        ItemStack playerHand = event.getPlayer().getInventory().getItemInMainHand();
        if(event.getAction().isRightClick() && playerHand != null)
        {
            if (playerHand.isSimilar(CustomItemManager.enchantRandomTicket.getItemStack()))
            {
                if(event.getPlayer().getInventory().firstEmpty() == -1)
                {
                    event.getPlayer().sendMessage(ChatColor.RED + "인벤토리에 빈 공간이 없습니다.");
                    return;
                }
                Random random = new Random();
                playerHand.setAmount(playerHand.getAmount() - 1);

                ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);
                EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) book.getItemMeta();
                Object[] keys = enchantBookMap.keySet().toArray();
                Enchantment randomEnchantment = (Enchantment) keys[random.nextInt(keys.length)];
                bookMeta.addStoredEnchant(randomEnchantment, enchantBookMap.get(randomEnchantment), true);
                book.setItemMeta(bookMeta);

                event.getPlayer().getInventory().addItem(book);
            }
        }
    }
}
