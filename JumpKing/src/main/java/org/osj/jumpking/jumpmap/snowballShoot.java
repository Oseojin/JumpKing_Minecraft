package org.osj.jumpking.jumpmap;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.osj.jumpking.JumpKing;

import java.util.LinkedList;
import java.util.List;

public class snowballShoot implements Listener
{
    private List<Player> flyingPlayerList = new LinkedList<>();

    @EventHandler
    public void hitSnowBallOnJumpmap(ProjectileHitEvent event)
    {
        if(event.getHitEntity() == null || !(event.getHitEntity() instanceof Player) || !event.getHitEntity().getWorld().getName().equals("JUMPMAP") || !event.getEntity().getType().equals(EntityType.SNOWBALL))
        {
            return;
        }
        Player player = ((Player) event.getHitEntity());

        player.sendMessage(ChatColor.GREEN + "눈덩이를 맞았습니다! 5초동안 낙하데미지에 면역됩니다!");

        flyingPlayerList.add(player);

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                flyingPlayerList.remove(player);
            }
        }.runTaskLater(JumpKing.getServerInstance(), 100L);

        player.setVelocity(new Vector(0,1,0).multiply(5));
    }

    @EventHandler
    public void playerDamageWithSnowBall(EntityDamageByEntityEvent event)
    {
        if(event.getEntity() == null || !(event.getEntity() instanceof Player) || !(event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) || flyingPlayerList.contains((Player)event.getEntity()))
        {
            return;
        }

        event.setCancelled(true);
    }
}
