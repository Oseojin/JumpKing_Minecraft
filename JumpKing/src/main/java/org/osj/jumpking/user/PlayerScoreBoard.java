package org.osj.jumpking.user;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.osj.jumpking.user.management.controller.UserManagementController;
import org.osj.jumpking.user.management.entity.User;

public class PlayerScoreBoard
{
    private static Scoreboard board;
    private static Objective obj;
    private static Score score;

    public static void setScoreboard(Player player)
    {
        User user = UserManagementController.getUserManager().getUserData(player);
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

        board = scoreboardManager.getNewScoreboard();

        obj = board.registerNewObjective("playerScoreboard", player.getName());
        score = obj.getScore(ChatColor.DARK_GREEN + "=------------------=");
        score.setScore(5);
        score = obj.getScore(ChatColor.AQUA + "이름: [" + user.getDisplayName() + "]");
        score.setScore(4);
        score = obj.getScore(ChatColor.GOLD + "골드: [" + user.getGold() + "]");
        score.setScore(3);
        score = obj.getScore(ChatColor.GREEN + "점핑코인: [" + user.getJumpingCoin() + "]");
        score.setScore(2);
        score = obj.getScore(ChatColor.YELLOW + "최대높이: [" + user.getMaxHeight() + "m]");
        score.setScore(1);
        score = obj.getScore(ChatColor.DARK_GREEN + "--------------------");
        score.setScore(0);
        obj.setDisplayName(ChatColor.GREEN + "MIKMIK 3::JUMPKING");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(board);
    }

}
