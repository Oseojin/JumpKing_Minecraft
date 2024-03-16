package org.osj.jumpking.user;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.osj.jumpking.CustomItemManager;

public class PlayerDeath implements Listener
{
    @EventHandler
    public void PlayerDeath(PlayerDeathEvent event)
    {
        // 점프맵이면 시작점으로 텔레포트 시키기
        if(event.getPlayer().getWorld().getName().equals("JUMPMAP"))
        {
            return;
        }
        // 인벤토리 세이브 권 있는지 확인
        if(event.getPlayer().getInventory().containsAtLeast(CustomItemManager.inventorySaveTicket.getItemStack(), 1))
        {
            event.getDrops().clear();
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            for(int i = 0; i < event.getPlayer().getInventory().getSize(); i++)
            {
                ItemStack currItem = event.getPlayer().getInventory().getItem(i);
                if(currItem == null)
                {
                    continue;
                }
                if(currItem.isSimilar(CustomItemManager.inventorySaveTicket.getItemStack()))
                {
                    currItem.setAmount(currItem.getAmount()-1);
                    return;
                }
            }
        }
    }
}
