package org.osj.jumpking.user.management.controller;

import org.osj.jumpking.JumpKing;
import org.osj.jumpking.user.management.controller.commands.UserInfoCommand;
import org.osj.jumpking.user.management.service.UserManager;

public class UserManagementController
{
    private static UserManager userManager;

    private final JumpKing serverInstance;

    private UserConnectionController userConnectionController;

    public UserManagementController()
    {
        this.userManager = new UserManager();
        this.serverInstance = JumpKing.getServerInstance();

        this.userConnectionController = new UserConnectionController(userManager);

        registerEvents();
        registerCommands();
    }

    private void registerEvents()
    {
        serverInstance.getServer().getPluginManager().registerEvents(userConnectionController, serverInstance);
    }
    private void registerCommands()
    {
        serverInstance.getServer().getPluginCommand("uinfo").setExecutor(new UserInfoCommand(userManager));
    }
}
