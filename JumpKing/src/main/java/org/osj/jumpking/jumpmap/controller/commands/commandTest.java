package org.osj.jumpking.jumpmap.controller.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class commandTest implements CommandExecutor, Listener
{
    private boolean click = false;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        Player player = (Player) sender;

        if(!player.isOp())
        {
            player.sendMessage("OP 전용 명령어 입니다.");
            return false;
        }

        click = !click;

        return false;
    }

    @EventHandler
    public void clickedBlockPos(PlayerInteractEvent event)
    {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && click)
        {
            event.getPlayer().sendMessage("" + event.getClickedBlock().getLocation());
        }
    }

}
