package org.osj.jumpking.village.controller;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.osj.jumpking.privateland.service.UserLandData;

public class InteractVillage implements Listener
{
    private final UserLandData userLandData;
    public InteractVillage(UserLandData userLandData)
    {
        this.userLandData = userLandData;
    }

    @EventHandler
    public void onInteractVillage(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();

        if(player.getWorld().getName().equals("village") && !player.isOp())
        {
            if(event.getClickedBlock() == null)
            {
                return;
            }
            Chunk interactChunk = event.getClickedBlock().getChunk();
            if(userLandData.containLand(player.getUniqueId(), interactChunk))
            {
                return;
            }

            player.sendMessage(ChatColor.RED + "소유하지 않은 청크는 상호작용 할 수 없습니다.");
            event.setCancelled(true);
        }
    }
}
