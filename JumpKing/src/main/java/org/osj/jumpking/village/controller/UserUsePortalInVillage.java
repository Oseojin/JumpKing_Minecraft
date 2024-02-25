package org.osj.jumpking.village.controller;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerPortalEvent;

public class UserUsePortalInVillage implements Listener
{
    @EventHandler
    public void EntityUsePortal(EntityPortalEvent event)
    {
        if(event.getFrom().getWorld().getName().equals("village"))
        {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void UserUsePortal(PlayerPortalEvent event)
    {
        if(event.getFrom().getWorld().getName().equals("village"))
        {
            event.setCancelled(true);
        }
    }
}
