package org.osj.jumpking.privateland.controller;

import org.osj.jumpking.JumpKing;
import org.osj.jumpking.privateland.controller.commands.LandPurchaseCommand;
import org.osj.jumpking.privateland.controller.commands.LandRemoveCommand;
import org.osj.jumpking.privateland.service.UserLandData;

public class UserLandManagementController
{
    private final JumpKing serverInstance;
    private static UserLandData userLandData;

    public UserLandManagementController()
    {
        this.serverInstance = JumpKing.getServerInstance();
        this.userLandData = new UserLandData();

        registerEvents();
        registerCommands();
    }

    private void registerEvents()
    {

    }
    private void registerCommands()
    {
        serverInstance.getServer().getPluginCommand("purchaseLand").setExecutor(new LandPurchaseCommand(userLandData));
        serverInstance.getServer().getPluginCommand("removeLand").setExecutor(new LandRemoveCommand(userLandData));
    }

    public UserLandData getUserLandData()
    {
        return userLandData;
    }
}
