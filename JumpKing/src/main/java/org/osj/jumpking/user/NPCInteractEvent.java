package org.osj.jumpking.user;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class NPCInteractEvent implements Listener
{
    @EventHandler
    public void PlayerInteractNPC(PlayerInteractEntityEvent event)
    {
        if(event.getRightClicked().getType().equals(EntityType.VILLAGER) || event.getRightClicked().getType().equals(EntityType.WANDERING_TRADER))
        {
            event.getPlayer().sendMessage(ChatColor.RED + "주민과의 상호작용은 금지되어 있습니다.");
            event.setCancelled(true);
        }
    }
}
