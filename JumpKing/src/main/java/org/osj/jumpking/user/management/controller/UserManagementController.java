package org.osj.jumpking.user.management.controller;

import org.osj.jumpking.JumpKing;
import org.osj.jumpking.user.*;
import org.osj.jumpking.user.management.controller.commands.UserInfoCommand;
import org.osj.jumpking.user.management.controller.commands.UserInviteCommand;
import org.osj.jumpking.user.management.service.UserManager;

public class UserManagementController
{
    private static UserManager userManager;
    private static UserManagementController instance;

    private final JumpKing serverInstance;

    private UserConnectionController userConnectionController;

    public UserManagementController()
    {
        this.userManager = new UserManager();
        this.serverInstance = JumpKing.getServerInstance();

        this.userConnectionController = new UserConnectionController(userManager);
        instance = this;

        registerEvents();
        registerCommands();
    }

    public static UserManager getUserManager()
    {
        return userManager;
    }

    private void registerEvents()
    {
        serverInstance.getServer().getPluginManager().registerEvents(userConnectionController, serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new NPCInteractEvent(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new PlayerDeath(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new PlayerChatEvent(userManager), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new PlayerPortal(userManager), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new PVPEvent(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new UseRandomBox(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new UsePouch(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new PlayerFishing(), serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(new UseEnchantRandom(), serverInstance);
    }
    private void registerCommands()
    {
        serverInstance.getServer().getPluginCommand("uinfo").setExecutor(new UserInfoCommand(userManager));
        serverInstance.getServer().getPluginCommand("invite").setExecutor(new UserInviteCommand());
    }
}
