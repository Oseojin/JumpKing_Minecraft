package org.osj.jumpking.user.management.entity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.osj.jumpking.db.db_connect;

import java.util.UUID;

public class User
{
    private UUID uuid;
    private String displayName;

    private Long gold;
    private Long jumpingCoin;

    private int maxHeight;

    private String prefix;

    public User(UUID uuid, String displayName, Long gold, Long jumpingCoin, Integer maxHeight)
    {
        this.uuid = uuid;
        this.displayName = displayName;
        this.gold = gold;
        this.jumpingCoin = jumpingCoin;
        this.maxHeight = maxHeight;
        changePrefix();
    }

    private void changePrefix()
    {
        if(maxHeight < 50)
        {
            prefix = ChatColor.YELLOW + "";
        }
        else if(maxHeight < 100)
        {
            prefix = ChatColor.GREEN + "";
        }
        else if(maxHeight < 150)
        {
            prefix = ChatColor.AQUA + "";
        }
        else if(maxHeight < 200)
        {
            prefix = ChatColor.LIGHT_PURPLE + "";
        }
        else if(maxHeight < 250)
        {
            prefix = ChatColor.RED + "";
        }

        prefix += "[" + maxHeight + "m]";
        Bukkit.getPlayer(uuid).setPlayerListName(prefix + ChatColor.WHITE + displayName);
    }

    public String getPrefix()
    {
        return prefix;
    }
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }
    public UUID getUuid()
    {
        return uuid;
    }
    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }
    public String getDisplayName()
    {
        return displayName;
    }
    public void loadDisplayName(String displayName)
    {
        this.displayName = displayName;
    }
    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
        db_connect.getInstance().SetDisplayName(Bukkit.getPlayer(uuid), this.displayName);
    }
    public Long getJumpingCoin()
    {
        return jumpingCoin;
    }
    public void loadJumpingCoin(Long jumpingCoin)
    {
        this.jumpingCoin = jumpingCoin;
    }
    public void setJumpingCoin(Long jumpingCoin)
    {
        this.jumpingCoin = jumpingCoin;
        db_connect.getInstance().SetJumpingCoin(Bukkit.getPlayer(uuid), this.jumpingCoin);
    }
    public Long getGold()
    {
        return gold;
    }
    public void loadGold(Long gold)
    {
        this.gold = gold;
    }
    public void setGold(Long gold)
    {
        this.gold = gold;
        db_connect.getInstance().SetGold(Bukkit.getPlayer(uuid), this.gold);
    }
    public Integer getMaxHeight()
    {
        return maxHeight;
    }
    public void loadMaxHeight(Integer maxHeight)
    {
        this.maxHeight = maxHeight;
        changePrefix();
    }
    public void setMaxHeight(Integer maxHeight)
    {
        this.maxHeight = maxHeight;
        db_connect.getInstance().SetMaxHeight(Bukkit.getPlayer(uuid), this.maxHeight);
        changePrefix();
    }
}
