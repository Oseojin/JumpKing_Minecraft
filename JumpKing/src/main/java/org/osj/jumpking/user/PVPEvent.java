package org.osj.jumpking.user;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

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

    @EventHandler
    public void onPlayerHitProjectile(ProjectileHitEvent event)
    {
        if(event.getHitEntity() instanceof Player && event.getEntity().getShooter() instanceof Player)
        {
            event.setCancelled(true);
        }
    }
}
