package kro.dodoworld.survivalweapons.items.custom;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class UnPlaceableBlocks implements Listener {
    @EventHandler
    public void onPlace(PlayerInteractEvent event){
        if(!event.isBlockInHand()) return;
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if(!event.getItem().hasItemMeta()) return;
        if(event.getItem().getItemMeta() == null) return;
        if(event.getItem().getItemMeta().getLore() == null) return;
        if(event.getItem().getItemMeta().getLore().contains(ChatColor.GRAY + "이 아이템은 설치가 불가능합니다.")){
            event.setCancelled(true);
        }
    }
}