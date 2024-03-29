package org.osj.jumpking;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.osj.jumpking.db.ConfigManager;
import org.osj.jumpking.jumpmap.controller.JumpMapManagementController;
import org.osj.jumpking.privateland.controller.UserLandManagementController;
import org.osj.jumpking.shop.controller.ShopManagementController;
import org.osj.jumpking.user.management.controller.UserManagementController;
import org.osj.jumpking.village.controller.UserUsePortalInVillage;
import org.osj.jumpking.village.controller.VillageManagementController;

public final class JumpKing extends JavaPlugin
{
    private static JumpKing serverInstance;
    private static UserManagementController userManagement;
    private static JumpMapManagementController jumpMapManagement;
    private static VillageManagementController villageManagement;
    private static UserLandManagementController userLandManagement;
    private static ShopManagementController shopManagement;
    private static SpawnLocManager spawnLocManager;
    private static ConfigManager configManager;

    @Override
    public void onEnable()
    {
        serverInstance = this;
        configManager = new ConfigManager();
        userManagement = new UserManagementController();
        userLandManagement = new UserLandManagementController();
        jumpMapManagement = new JumpMapManagementController();
        villageManagement = new VillageManagementController();
        shopManagement = new ShopManagementController();
        spawnLocManager = new SpawnLocManager();

        getServer().getPluginManager().registerEvents(new CustomItemManager(), serverInstance);
        getServer().getPluginManager().registerEvents(new BlockBreak(), serverInstance);

        Location wildSpawnBlockLoc = new Location(SpawnLocManager.getWildSpawnLoc().getWorld(), SpawnLocManager.getWildSpawnLoc().getX(), SpawnLocManager.getWildSpawnLoc().getY() + 2, SpawnLocManager.getWildSpawnLoc().getZ());
        Bukkit.getWorld("wild").getBlockAt(wildSpawnBlockLoc).setType(Material.VERDANT_FROGLIGHT);

        getLogger().info("플러그인 시작 테스트");
    }

    @Override
    public void onDisable()
    {
        serverInstance = null;
        userManagement = null;
        jumpMapManagement = null;
        villageManagement = null;
        userLandManagement = null;
        shopManagement = null;
        configManager = null;
        spawnLocManager = null;

        getLogger().info("플러그인 종료 테스트");
    }

    public static JumpKing getServerInstance()
    {
        return serverInstance;
    }

    public static UserManagementController getUserManagement()
    {
        return userManagement;
    }
    public static JumpMapManagementController getJumpMapManagement()
    {
        return jumpMapManagement;
    }
    public static VillageManagementController getVillageManagement()
    {
        return villageManagement;
    }
    public static UserLandManagementController getUserLandManagement()
    {
        return userLandManagement;
    }
    public static ConfigManager getConfigManager()
    {
        return configManager;
    }
}
