package org.osj.jumpking.jumpmap.controller;

import org.osj.jumpking.JumpKing;
import org.osj.jumpking.jumpmap.controller.commands.commandTest;
import org.osj.jumpking.jumpmap.snowballShoot;

public class JumpMapManagementController
{
    private final JumpKing serverInstance;
    private final commandTest testCommand;

    public JumpMapManagementController()
    {
        this.serverInstance = JumpKing.getServerInstance();

        testCommand = new commandTest();

        registerEvents();
        registerCommands();
    }

    private void registerEvents()
    {
        serverInstance.getServer().getPluginManager().registerEvents(new UserJump(serverInstance.getServer().getWorld("JUMPMAP")), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new snowballShoot(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(testCommand, serverInstance);
    }
    private void registerCommands()
    {
        serverInstance.getServer().getPluginCommand("test").setExecutor(testCommand);
    }
}
