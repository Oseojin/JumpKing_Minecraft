package org.osj.jumpking.village.controller;

import org.osj.jumpking.JumpKing;
import org.osj.jumpking.privateland.service.UserLandData;

public class VillageManagementController
{
    private final JumpKing serverInstance;

    public VillageManagementController()
    {
        this.serverInstance = JumpKing.getServerInstance();

        registerEvents();
        registerCommands();
    }

    private void registerEvents()
    {
        serverInstance.getServer().getPluginManager().registerEvents(new UserUsePortalInVillage(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new InteractVillage(serverInstance.getUserLandManagement().getUserLandData()), serverInstance);
    }
    private void registerCommands()
    {

    }
}
