package org.osj.jumpking.privateland.service;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.osj.jumpking.JumpKing;
import org.osj.jumpking.db.ConfigManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class UserLandData
{
    FileConfiguration landConfig = JumpKing.getConfigManager().getConfig("privateland");
    private HashMap<UUID, List<Long>> userLandDataMap = new HashMap<>();

    public UserLandData()
    {
        loadFromConfig();
    }

    public void addLand(UUID uuid, Chunk chunk)
    {
        if(userLandDataMap.get(uuid) == null)
        {
            List<Long> newChunkList = new LinkedList<>();
            newChunkList.add(chunk.getChunkKey());
            userLandDataMap.put(uuid, newChunkList);
        }
        else
        {
            userLandDataMap.get(uuid).add(chunk.getChunkKey());
        }

        landConfig.set("chunks." + chunk.getChunkKey(), uuid.toString());
        JumpKing.getConfigManager().saveConfig("privateland");
    }

    private void loadFromConfig()
    {
        // Config에 청크 키 값들 저장해서 해시맵에 다시 다 집어넣기
        if(landConfig.getConfigurationSection("chunks.") == null)
        {
            return;
        }

        List<String> configChunkKeyList = landConfig.getConfigurationSection("chunks.").getKeys(true).stream().toList();
        for(int i = 0; i < configChunkKeyList.size(); i++)
        {
            UUID uuid = UUID.fromString(landConfig.getString("chunks." + configChunkKeyList.get(i)));
            Chunk chunk = Bukkit.getWorld("village").getChunkAt(Long.parseLong(configChunkKeyList.get(i)));
            addLand(uuid, chunk);
        }
    }

    public void removeLand(UUID uuid, Chunk chunk)
    {
        if(!containLand(uuid, chunk))
        {
            return;
        }

        userLandDataMap.get(uuid).remove(chunk.getChunkKey());
        landConfig.set("chunks." + chunk.getChunkKey(), null);
        JumpKing.getConfigManager().saveConfig("privateland");
    }

    public boolean containLand(UUID player, Chunk chunk)
    {
        if(!userLandDataMap.containsKey(player) || !userLandDataMap.get(player).contains(chunk.getChunkKey()))
        {
            return false;
        }

        return true;
    }

    public boolean containLandAllOfPlayer(Chunk chunk)
    {
        for(UUID key : userLandDataMap.keySet())
        {
            if(userLandDataMap.get(key).contains(chunk.getChunkKey()))
            {
                return true;
            }
        }
        return false;
    }
}
