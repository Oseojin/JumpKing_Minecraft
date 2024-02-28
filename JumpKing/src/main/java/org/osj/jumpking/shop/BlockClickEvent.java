package org.osj.jumpking.shop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.osj.jumpking.JumpKing;
import org.osj.jumpking.jumpmap.controller.UserJump;
import org.osj.jumpking.shop.shopBundle.*;
import org.osj.jumpking.user.management.controller.UserManagementController;

import java.util.ArrayList;
import java.util.List;

public class BlockClickEvent implements Listener
{
    private final List<Block> mineralShopBlock = new ArrayList<>();
    private final List<Block> wildGoldRandomShopBlock = new ArrayList<>();
    private final List<Block> jumpmapGoldRandomShopBlock = new ArrayList<>();
    private final List<Block> wildJumpingRandomShopBlock = new ArrayList<>();
    private final List<Block> jumpmapJumpingRandomShopBlock = new ArrayList<>();
    private final List<Block> villageToWildBlock = new ArrayList<>();
    private final List<Block> villageToJumpmapBlock = new ArrayList<>();
    private final List<Block> wildToVillageBlock = new ArrayList<>();
    private final List<Block> jumpmapToVillageBlock = new ArrayList<>();
    private final List<Block> jumpingShoesUpgradeBlock = new ArrayList<>();

    private static Location villageSpawnLoc = new Location(Bukkit.getWorld("village"), 527, 72 ,-202);
    private static Location wildSpawnLoc = new Location(Bukkit.getWorld("wild"), -272, 66 ,-224);

    public BlockClickEvent()
    {
        mineralShopBlock.add(Bukkit.getWorld("village").getBlockAt(557, 74, -193));
        wildGoldRandomShopBlock.add(Bukkit.getWorld("village").getBlockAt(557, 74, -192));
        wildJumpingRandomShopBlock.add(Bukkit.getWorld("village").getBlockAt(557, 74, -191));
        villageToWildBlock.add(Bukkit.getWorld("village").getBlockAt(557,74,-186));
        villageToJumpmapBlock.add(Bukkit.getWorld("village").getBlockAt(557,74,-185));

        jumpmapGoldRandomShopBlock.add(Bukkit.getWorld("JUMPMAP").getBlockAt(-54, -59, -8));
        jumpmapJumpingRandomShopBlock.add(Bukkit.getWorld("JUMPMAP").getBlockAt(-54, -59, -12));
        jumpmapToVillageBlock.add(Bukkit.getWorld("JUMPMAP").getBlockAt(-45, -59, -4));
        jumpingShoesUpgradeBlock.add(Bukkit.getWorld("JUMPMAP").getBlockAt(-49, -59, -4));

        wildToVillageBlock.add(Bukkit.getWorld("wild").getBlockAt(-277, 64, -226));
    }

    @EventHandler
    public void clickBlockEvent(PlayerInteractEvent event)
    {
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            return;
        }
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if(mineralShopBlock.contains(clickedBlock))
        {
            new MineralShop().open(player);
        }
        else if(wildGoldRandomShopBlock.contains(clickedBlock))
        {
            new WildGoldRandomBoxShop().open(player);
        }
        else if(wildJumpingRandomShopBlock.contains(clickedBlock))
        {
            new WildJumpingRandomBoxShop().open(player);
        }
        else if(jumpmapGoldRandomShopBlock.contains(clickedBlock))
        {
            new JumpmapGoldRandomBoxShop().open(player);
        }
        else if(jumpmapJumpingRandomShopBlock.contains(clickedBlock))
        {
            new JumpmapJumpingRandomBoxShop().open(player);
        }
        else if(jumpingShoesUpgradeBlock.contains(clickedBlock))
        {
            new ShoesUpgradeShop().open(player);
        }

        // 텔포 블럭
        else if(villageToWildBlock.contains(clickedBlock))
        {
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    player.teleport(wildSpawnLoc);
                }
            }.runTaskLater(JumpKing.getServerInstance(), 1L);
        }
        else if(villageToJumpmapBlock.contains(clickedBlock))
        {
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    player.teleport(UserJump.getSpawnLoc(UserManagementController.getUserManager().getUserData(player).getMaxHeight()));
                }
            }.runTaskLater(JumpKing.getServerInstance(), 1L);
        }
        else if(wildToVillageBlock.contains(clickedBlock))
        {
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    player.teleport(villageSpawnLoc);
                }
            }.runTaskLater(JumpKing.getServerInstance(), 1L);
        }
        else if(jumpmapToVillageBlock.contains(clickedBlock))
        {
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    player.teleport(villageSpawnLoc);
                }
            }.runTaskLater(JumpKing.getServerInstance(), 1L);
        }
    }
}
