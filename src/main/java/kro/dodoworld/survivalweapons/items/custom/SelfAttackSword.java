package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SelfAttackSword implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player player)) return;
        if(ItemsInit.isPluginItem("sw_item_self_attack_sword", player)){
            event.setCancelled(true);
            player.damage(event.getFinalDamage());
        }
    }
}
