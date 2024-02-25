package org.osj.jumpking.privateland.service;

import org.bukkit.Chunk;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class UserLandData
{
    private HashMap<UUID, List<Chunk>> userLandData = new HashMap<>();

    public void addLand(UUID uuid, Chunk chunk)
    {
        if(userLandData.get(uuid) == null)
        {
            List<Chunk> newChunkList = new LinkedList<>();
            newChunkList.add(chunk);
            userLandData.put(uuid, newChunkList);
        }
        else
        {
            userLandData.get(uuid).add(chunk);
        }
    }

    public void removeLand(UUID uuid, Chunk chunk)
    {
        if(!containLand(uuid, chunk))
        {
            return;
        }

        userLandData.get(uuid).remove(chunk);
    }

    public boolean containLand(UUID player, Chunk chunk)
    {
        if(!userLandData.containsKey(player) || !userLandData.get(player).contains(chunk))
        {
            return false;
        }

        return true;
    }

    public boolean containLandAllOfPlayer(Chunk chunk)
    {
        for(UUID key : userLandData.keySet())
        {
            if(userLandData.get(key).contains(chunk))
            {
                return true;
            }
        }
        return false;
    }
}
