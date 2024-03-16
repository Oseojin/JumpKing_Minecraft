package org.osj.jumpking;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener
{
    @EventHandler
    public void BlockBreak(BlockBreakEvent event)
    {
        if(event.getBlock().getLocation().equals(SpawnLocManager.getWildBlockLoc()))
        {
            event.setCancelled(true);
        }
    }
}
