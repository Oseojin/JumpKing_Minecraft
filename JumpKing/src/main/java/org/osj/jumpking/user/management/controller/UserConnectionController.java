package org.osj.jumpking.user.management.controller;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.osj.jumpking.JumpKing;
import org.osj.jumpking.user.management.service.UserManager;

public class UserConnectionController implements Listener
{
    private final UserManager userManager;

    UserConnectionController(UserManager userManager)
    {
        this.userManager = userManager;
    }

    @EventHandler
    public void onUserJoinServer(PlayerJoinEvent event)
    {
        userManager.addUser(event.getPlayer());
        JumpKing.getServerInstance().getLogger().info("플레이어 데이터 저장");
    }

    @EventHandler
    public void onUserQuitFromServer(PlayerQuitEvent event)
    {
        userManager.removeUser(event.getPlayer());
        JumpKing.getServerInstance().getLogger().info("플레이어 데이터 삭제");
    }
}
