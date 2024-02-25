package org.osj.jumpking.jumpmap.controller;

import org.osj.jumpking.JumpKing;

public class JumpMapManagementController
{
    private final JumpKing serverInstance;

    public JumpMapManagementController()
    {
        this.serverInstance = JumpKing.getServerInstance();

        registerEvents();
        registerCommands();
    }

    private void registerEvents()
    {
        serverInstance.getServer().getPluginManager().registerEvents(new UserJump(), serverInstance);
    }
    private void registerCommands()
    {

    }
}
