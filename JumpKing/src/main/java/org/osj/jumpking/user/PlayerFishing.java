package org.osj.jumpking.user;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerFishing implements Listener
{
    @EventHandler
    public void PlayerFishingEvent(PlayerFishEvent event)
    {
        if(event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH))
        {
            Item dropItem = (Item) event.getCaught();
            if(dropItem.getItemStack().getType().equals(Material.ENCHANTED_BOOK))
            {
                dropItem.setItemStack(new ItemStack(Material.TROPICAL_FISH, 1));
            }
        }
    }

}
