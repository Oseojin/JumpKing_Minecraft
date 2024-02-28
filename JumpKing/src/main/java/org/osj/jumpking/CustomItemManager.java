package org.osj.jumpking;

import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.Events.ItemsAdderLoadDataEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CustomItemManager implements Listener
{
    public static CustomStack jumpingShoes;
    public static CustomStack inventorySaveTicket;
    public static CustomStack landPurchaseTicket;
    public static CustomStack villageReturnTicket;
    public static CustomStack enchantRandomTicket;
    public static CustomStack wildGoldRandomBox;
    public static CustomStack jumpmapGoldRandomBox;
    public static CustomStack wildJumpingRandomBox;
    public static CustomStack jumpmapJumpingRandomBox;
    public static CustomStack jumpingCookie;
    public static CustomStack jumpingCoinPouch;
    public static CustomStack goldPouch;
    public static CustomStack reinforceStone;
    public static CustomStack shiningStone;
    public static CustomStack downPreventTicket;
    public static CustomStack userInviteTicket;

    @EventHandler
    public void onItemsAdderLoaded(ItemsAdderLoadDataEvent event)
    {
        jumpingShoes = CustomStack.getInstance("jumpking:jumping_shoes");
        inventorySaveTicket = CustomStack.getInstance("jumpking:inventory_save_ticket");
        landPurchaseTicket = CustomStack.getInstance("jumpking:land_purchase_ticket");
        villageReturnTicket = CustomStack.getInstance("jumpking:village_return_ticket");
        enchantRandomTicket = CustomStack.getInstance("jumpking:enchant_random_ticket");
        wildGoldRandomBox = CustomStack.getInstance("jumpking:wild_goldrandombox");
        jumpmapGoldRandomBox = CustomStack.getInstance("jumpking:jumpmap_goldrandombox");
        wildJumpingRandomBox = CustomStack.getInstance("jumpking:wild_jumpingrandombox");
        jumpmapJumpingRandomBox = CustomStack.getInstance("jumpking:jumpmap_jumpingrandombox");
        jumpingCookie = CustomStack.getInstance("jumpking:jumping_cookie");
        jumpingCoinPouch = CustomStack.getInstance("jumpking:jumping_coin_pouch");
        goldPouch = CustomStack.getInstance("jumpking:gold_pouch");
        reinforceStone = CustomStack.getInstance("jumpking:reinforce_stone");
        shiningStone = CustomStack.getInstance("jumpking:shining_stone");
        downPreventTicket = CustomStack.getInstance("jumpking:down_prevent_ticket");
        userInviteTicket = CustomStack.getInstance("jumpking:user_invite_ticket");
    }
}
