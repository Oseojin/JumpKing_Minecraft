package org.osj.jumpking.village.controller;

import org.osj.jumpking.JumpKing;

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
        serverInstance.getServer().getPluginManager().registerEvents(new UseVillageReturn(), serverInstance);
    }
    private void registerCommands()
    {

    }
}
