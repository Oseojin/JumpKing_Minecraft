package org.osj.jumpking.user.management.service;

import org.bukkit.entity.Player;
import org.osj.jumpking.user.management.entity.User;

import java.util.HashMap;

public class UserManager
{
    private HashMap<Player, User> onlineUserData = new HashMap<>();

    public void addUser(Player player)
    {
        User newUser = new User(
                player.getUniqueId(),
                player.getDisplayName(),
                0L,
                0L,
                0
        );

        onlineUserData.put(player, newUser);
    }

    public void removeUser(Player player)
    {
        onlineUserData.remove(player);
    }

    public User getUserData(Player player)
    {
        return onlineUserData.get(player);
    }
}