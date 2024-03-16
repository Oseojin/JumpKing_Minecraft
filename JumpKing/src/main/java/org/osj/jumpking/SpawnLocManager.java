package org.osj.jumpking;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class SpawnLocManager
{
    public static Location villageSpawnLoc;

    public SpawnLocManager()
    {
        villageSpawnLoc = new Location(Bukkit.getWorld("village"), 0, 5 ,0);
    }

    public static Location getWildSpawnLoc()
    {
        return Bukkit.getWorld("wild").getSpawnLocation();
    }
    public static Location getWildBlockLoc()
    {
        return new Location(SpawnLocManager.getWildSpawnLoc().getWorld(), SpawnLocManager.getWildSpawnLoc().getX(), SpawnLocManager.getWildSpawnLoc().getY() + 2, SpawnLocManager.getWildSpawnLoc().getZ());
    }

    public static Location getJumpmapSpawnLoc(int maxHeight)
    {
        Location spawnLoc;
        World world = Bukkit.getWorld("JUMPMAP");
        if(maxHeight < 50)
        {
            spawnLoc = new Location(world, -42, -60, -13);
        }
        else if(maxHeight < 100)
        {
            spawnLoc = new Location(world, -42, -10, -13);
        }
        else if(maxHeight < 150)
        {
            spawnLoc = new Location(world, -42, 40, -13);
        }
        else if(maxHeight < 200)
        {
            spawnLoc = new Location(world, 0, -60, 0);
        }
        else if(maxHeight < 250)
        {
            spawnLoc = new Location(world, 0, -60, 0);
        }
        else
        {
            spawnLoc = new Location(world, 0, -60, 0);
        }

        return spawnLoc;
    }
}
