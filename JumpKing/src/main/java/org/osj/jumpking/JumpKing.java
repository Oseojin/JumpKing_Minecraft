package org.osj.jumpking;

import org.bukkit.plugin.java.JavaPlugin;
import org.osj.jumpking.jumpmap.controller.JumpMapManagementController;
import org.osj.jumpking.privateland.controller.UserLandManagementController;
import org.osj.jumpking.user.management.controller.UserManagementController;
import org.osj.jumpking.village.controller.VillageManagementController;

public final class JumpKing extends JavaPlugin
{
    private static JumpKing serverInstance;
    private static UserManagementController userManagement;
    private static JumpMapManagementController jumpMapManagement;
    private static VillageManagementController villageManagement;
    private static UserLandManagementController userLandManagement;

    @Override
    public void onEnable()
    {
        serverInstance = this;
        userManagement = new UserManagementController();
        userLandManagement = new UserLandManagementController();
        jumpMapManagement = new JumpMapManagementController();
        villageManagement = new VillageManagementController();

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
}
