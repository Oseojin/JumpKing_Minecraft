package org.osj.jumpking.jumpmap.controller;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.osj.jumpking.JumpKing;
import org.osj.jumpking.jumpmap.ShoesStatManager;
import org.osj.jumpking.user.management.controller.UserManagementController;
import org.osj.jumpking.user.management.entity.User;

import java.util.HashMap;

public class UserJump implements Listener
{
    private HashMap<Player, Integer> playerMaxHeight = new HashMap<>();
    private HashMap<Player, Long> playerJumpHolding = new HashMap<>();
    private HashMap<Player, BukkitTask> playerBukkitTask = new HashMap<>();
    private final JumpKing serverInstance;
    private final World jumpMap;

    public UserJump(World jumpMap)
    {
        this.serverInstance = JumpKing.getServerInstance();
        this.jumpMap = jumpMap;
    }

    @EventHandler
    public void onShiftJump(PlayerToggleSneakEvent event)
    {
        Player player = event.getPlayer();
        BukkitTask playerHoldingTask;

        float maxHoldingTime = 30f;

        // 점프맵에 입장 한 상태인지 확인 || 신발 신고 있는지 확인 || 땅에 붙어있는지 확인
        if(!player.getWorld().getName().equals("JUMPMAP") || player.getInventory().getBoots() == null || !player.getWorld().getEntity(player.getUniqueId()).isOnGround())
        {
            return;
        }
        // 신발 부분 커스텀 아이템 가져오기
        CustomStack playerShoes = CustomStack.byItemStack(player.getInventory().getBoots());
        // 커스텀 아이템인지 확인 || 신발 권한을 가지고 있지 않으면 반환
        if(playerShoes == null || !playerShoes.getPermission().equals("ia.jumpking:jumping_shoes"))
        {
            return;
        }
        // 최고 높이 경신
        int playerHeight = (int)(player.getLocation().getY() + 60);
        if(!playerMaxHeight.containsKey(player))
        {
            playerMaxHeight.put(player, playerHeight);
        }
        else
        {
            if(playerMaxHeight.get(player) < playerHeight)
            {
                playerMaxHeight.put(player, playerHeight);
            }
        }
        // 신발 점프력 가져오기
        double shoesLv = 1.0 + (ShoesStatManager.getShoesLv(playerShoes.getItemStack()) / 10.0);
        //player.sendMessage("점프력: " + shoesLv);

        // 눌렀다!
        if(event.isSneaking())
        {
            playerHoldingTask = new BukkitRunnable()
            {
                int maxBar = 100;
                float count = 1;
                @Override
                public void run()
                {
                    // count / maxHoldingTime
                    String activeBar = ChatColor.GREEN + "";
                    int currBar;
                    if((long)(count / maxHoldingTime) % 2 == 1) // 홀수면
                    {
                        currBar = (int)((maxHoldingTime - (count % maxHoldingTime)) / maxHoldingTime * maxBar);
                    }
                    else // 짝수 또는 0이면
                    {
                        currBar = (int)((count % maxHoldingTime) / maxHoldingTime * maxBar);
                    }
                    for(int i = 0; i < currBar; i++)
                    {
                        activeBar += "|";
                    }
                    activeBar += ChatColor.WHITE + "";
                    for(int i = 0; i < maxBar - currBar; i++)
                    {
                        activeBar += "|";
                    }
                    player.sendActionBar(activeBar);
                    //player.sendMessage(count + "");
                    count++;
                }
            }.runTaskTimer(serverInstance, 0L, 1L);
            playerBukkitTask.put(player, playerHoldingTask);
            playerJumpHolding.put(player, jumpMap.getGameTime());
        }
        // 땠다!
        else
        {
            playerBukkitTask.get(player).cancel();
            long origin = playerJumpHolding.get(player);
            long curr = jumpMap.getGameTime();
            long holdingTime = curr - origin;
            double jumpPower;
            if((long)(holdingTime / maxHoldingTime) % 2 == 1) // 홀수면
            {
                jumpPower = (maxHoldingTime - (holdingTime % maxHoldingTime)) / maxHoldingTime * shoesLv;
            }
            else // 짝수 또는 0이면
            {
                jumpPower = (holdingTime % maxHoldingTime) / maxHoldingTime * shoesLv;
            }
            //player.sendMessage("누르고 있던 시간: " + holdingTime + " 점프 힘: " + jumpPower);
            player.setVelocity(player.getLocation().getDirection().multiply(jumpPower));
        }
    }

    @EventHandler
    public void PlayerFallDeath(PlayerDeathEvent event)
    {
        if(event.getPlayer().getWorld().getName().equals("JUMPMAP"))
        {
            User user = UserManagementController.getUserManager().getUserData(event.getPlayer());
            user.setJumpingCoin(user.getJumpingCoin() + playerMaxHeight.get(event.getPlayer()));
            if(user.getMaxHeight() < playerMaxHeight.get(event.getPlayer()))
            {
                user.setMaxHeight(playerMaxHeight.get(event.getPlayer()));
            }
            playerMaxHeight.put(event.getPlayer(), 0);
            event.setCancelled(true);

            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    event.getPlayer().teleport(getSpawnLoc(user.getMaxHeight()));
                }
            }.runTaskLater(serverInstance, 1L);
        }
    }

    public static Location getSpawnLoc(int maxHeight)
    {
        Location spawnLoc;
        World world = Bukkit.getWorld("JUMPMAP");
        if(maxHeight < 50)
        {
            spawnLoc = new Location(world, -42, -60, -13);
        }
        else if(maxHeight < 100)
        {
            spawnLoc = new Location(world, -42, -10, -13);
        }
        else if(maxHeight < 150)
        {
            spawnLoc = new Location(world, -42, 40, -13);
        }
        else if(maxHeight < 200)
        {
            spawnLoc = new Location(world, 0, -60, 0);
        }
        else if(maxHeight < 250)
        {
            spawnLoc = new Location(world, 0, -60, 0);
        }
        else
        {
            spawnLoc = new Location(world, 0, -60, 0);
        }

        return spawnLoc;
    }

    @EventHandler
    public void PlayerEnterJumpMap(PlayerChangedWorldEvent event)
    {
        Player player = event.getPlayer();
        if(player.getWorld().getName().equals("JUMPMAP"))
        {
            player.setWalkSpeed(0);
            player.setMaxHealth(2.0);
        }
        else if(event.getFrom().getName().equals("JUMPMAP"))
        {
            player.setWalkSpeed(0.2f);
            player.setMaxHealth(20.0);
            player.setHealth(20.0);
        }
    }

    @EventHandler
    public void LockPlayerJump(PlayerJumpEvent event)
    {
        Player player = event.getPlayer();
        if(player.getWorld().getName().equals("JUMPMAP"))
        {
            event.setCancelled(true);
        }
    }
}
