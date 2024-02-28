package org.osj.jumpking.user;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PVPEvent implements Listener
{
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event)
    {
        if(event.getEntity() instanceof Player && event.getDamager() instanceof  Player)
        {
            event.setCancelled(true);
        }
    }
}
