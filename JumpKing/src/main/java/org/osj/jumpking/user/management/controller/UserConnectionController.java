package org.osj.jumpking.user.management.controller;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.osj.jumpking.JumpKing;
import org.osj.jumpking.db.ConfigManager;
import org.osj.jumpking.db.db_connect;
import org.osj.jumpking.user.management.service.UserManager;
import org.osj.jumpking.user.PlayerScoreBoard;

public class UserConnectionController implements Listener
{
    private final UserManager userManager;

    UserConnectionController(UserManager userManager)
    {
        this.userManager = userManager;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        Player player = event.getPlayer();
        String playerName = player.getName();

        if(!JumpKing.getConfigManager().getConfig("whitelist").contains("players."+playerName.toLowerCase()))
        {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "초대받지 못했습니다!");
        }
    }

    @EventHandler
    public void onUserJoinServer(PlayerJoinEvent event)
    {
        userManager.addUser(event.getPlayer());
        JumpKing.getServerInstance().getLogger().info("플레이어 데이터 저장");

        if(db_connect.getInstance().insertMember(event.getPlayer()) == 0)
        {
            db_connect.getInstance().db_PlayerInfo(event.getPlayer());
            PlayerScoreBoard.setScoreboard(event.getPlayer());
        }
        else
        {
            event.getPlayer().kick(Component.text().content("데이터베이스에서 정보를 로드중 오류가 발생 했습니다. " + db_connect.getInstance().insertMember(event.getPlayer())).build());
        }
    }

    @EventHandler
    public void onUserQuitFromServer(PlayerQuitEvent event)
    {
        userManager.removeUser(event.getPlayer());
        JumpKing.getServerInstance().getLogger().info("플레이어 데이터 삭제");
    }
}
