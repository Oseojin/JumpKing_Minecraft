package org.osj.jumpking.jumpmap.controller;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class UserJump implements Listener
{
    @EventHandler
    public void onShiftJump(PlayerToggleSneakEvent event)
    {
        Player player = event.getPlayer();

        player.sendMessage(player.getWorld().getName());
        // 점프맵에 입장 한 상태인지 확인
        if(!player.getWorld().getName().equals("JUMPMAP"))
        {
            return;
        }
        if(event.isSneaking()) // 방금 누른 상태이면 반환
        {
            return;
        }

        player.setVelocity(player.getLocation().getDirection().multiply(1));
    }
}
