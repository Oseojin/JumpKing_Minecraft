package org.osj.jumpking.village.controller;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.osj.jumpking.CustomItemManager;
import org.osj.jumpking.JumpKing;

public class UseVillageReturn implements Listener
{
    private static Location villageSpawnLoc = new Location(Bukkit.getWorld("village"), 527, 72 ,-202);
    @EventHandler
    public void useVillageReturn(PlayerInteractEvent event)
    {
        ItemStack playerHand = event.getPlayer().getInventory().getItemInMainHand();
        if(event.getAction().isRightClick() && playerHand != null)
        {
            if(playerHand.isSimilar(CustomItemManager.villageReturnTicket.getItemStack()))
            {
                playerHand.setAmount(playerHand.getAmount()-1);
                new BukkitRunnable()
                {
                    @Override
                    public void run()
                    {
                        event.getPlayer().teleport(villageSpawnLoc);
                    }
                }.runTaskLater(JumpKing.getServerInstance(), 1L);
            }
        }
    }
}
