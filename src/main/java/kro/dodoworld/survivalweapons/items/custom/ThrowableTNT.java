package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ThrowableTNT implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(!ItemsInit.hasLore(ChatColor.RED + "우클릭으로 TNT를 던질 수 있다.", event.getPlayer())) return;

            Player player = event.getPlayer();
            TNTPrimed tnt = player.getWorld().spawn(player.getLocation(), TNTPrimed.class);
            tnt.setSource(player);
            tnt.setFuseTicks(60);
            tnt.setVelocity(player.getLocation().getDirection().multiply(2));
            event.getItem().setAmount(event.getItem().getAmount() - 1);
        }
    }
}
