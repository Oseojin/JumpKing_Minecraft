package org.osj.jumpking.shop;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.osj.jumpking.JumpKing;
import org.osj.jumpking.SpawnLocManager;
import org.osj.jumpking.jumpmap.controller.UserJump;
import org.osj.jumpking.shop.shopBundle.*;
import org.osj.jumpking.user.management.controller.UserManagementController;

import java.util.ArrayList;
import java.util.List;

public class BlockClickEvent implements Listener
{
    private final List<Block> mineralShopBlock = new ArrayList<>();
    private final List<Block> cropShopBlock = new ArrayList<>();
    private final List<Block> monsterShopBlock = new ArrayList<>();
    private final List<Block> animalShopBlock = new ArrayList<>();
    private final List<Block> wildGoldRandomShopBlock = new ArrayList<>();
    private final List<Block> jumpmapGoldRandomShopBlock = new ArrayList<>();
    private final List<Block> wildJumpingRandomShopBlock = new ArrayList<>();
    private final List<Block> jumpmapJumpingRandomShopBlock = new ArrayList<>();
    private final List<Block> villageToWildBlock = new ArrayList<>();
    private final List<Block> villageToJumpmapBlock = new ArrayList<>();
    private final List<Block> wildToVillageBlock = new ArrayList<>();
    private final List<Block> jumpmapToVillageBlock = new ArrayList<>();
    private final List<Block> jumpingShoesUpgradeBlock = new ArrayList<>();

    public BlockClickEvent()
    {
        // 상점
        mineralShopBlock.add(Bukkit.getWorld("village").getBlockAt(-9, 1, -64));
        cropShopBlock.add(Bukkit.getWorld("village").getBlockAt(-9, 1, -74));
        monsterShopBlock.add(Bukkit.getWorld("village").getBlockAt(8, 1, -74));
        animalShopBlock.add(Bukkit.getWorld("village").getBlockAt(8, 1, -64));

        // 야생 랜덤박스들
        wildGoldRandomShopBlock.add(Bukkit.getWorld("village").getBlockAt(-2, 1, -80));
        wildJumpingRandomShopBlock.add(Bukkit.getWorld("village").getBlockAt(1, 1, -80));

        // 마을에서 다른월드
        villageToWildBlock.add(Bukkit.getWorld("village").getBlockAt(70,1,-1));
        villageToJumpmapBlock.add(Bukkit.getWorld("village").getBlockAt(-72, 1, 0));
        villageToJumpmapBlock.add(Bukkit.getWorld("village").getBlockAt(-72, 1, -1));

        // 점프맵
        jumpmapToVillageBlock.add(Bukkit.getWorld("JUMPMAP").getBlockAt(-45, -9, -4));
        jumpmapToVillageBlock.add(Bukkit.getWorld("JUMPMAP").getBlockAt(-45, -59, -4));

        jumpingShoesUpgradeBlock.add(Bukkit.getWorld("JUMPMAP").getBlockAt(-50, -59, -4));
        jumpingShoesUpgradeBlock.add(Bukkit.getWorld("JUMPMAP").getBlockAt(-50, -9, -4));

        jumpmapGoldRandomShopBlock.add(Bukkit.getWorld("JUMPMAP").getBlockAt(-54, -59, -8));
        jumpmapGoldRandomShopBlock.add(Bukkit.getWorld("JUMPMAP").getBlockAt(-54, -9, -8));

        jumpmapJumpingRandomShopBlock.add(Bukkit.getWorld("JUMPMAP").getBlockAt(-54, -59, -13));
        jumpmapJumpingRandomShopBlock.add(Bukkit.getWorld("JUMPMAP").getBlockAt(-54, -9, -13));

        // 야생에서 마을
        wildToVillageBlock.add(SpawnLocManager.getWildBlockLoc().getBlock());
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

        // 판매 상점
        if(mineralShopBlock.contains(clickedBlock))
        {
            new MineralShop().open(player);
        }
        else if(cropShopBlock.contains(clickedBlock))
        {
            new CropShop().open(player);
        }
        else if(monsterShopBlock.contains(clickedBlock))
        {
            new MonsterShop().open(player);
        }
        else if(animalShopBlock.contains(clickedBlock))
        {
            new AnimalShop().open(player);
        }

        // 구매 상점
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

        // 업그레이드 블럭
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
                    player.teleport(SpawnLocManager.getWildSpawnLoc());
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
                    player.teleport(SpawnLocManager.getJumpmapSpawnLoc(UserManagementController.getUserManager().getUserData(player).getMaxHeight()));
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
                    player.teleport(SpawnLocManager.villageSpawnLoc);
                }
            }.runTaskLater(JumpKing.getServerInstance(), 1L);
        }
        else if(jumpmapToVillageBlock.contains(clickedBlock))
        {
            UserJump.UpdateMaxHeight(player);

            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    player.teleport(SpawnLocManager.villageSpawnLoc);
                }
            }.runTaskLater(JumpKing.getServerInstance(), 1L);
        }
    }
}
