package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class LightingSword implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        if(event.getEntity() instanceof LivingEntity){
            Player living = (Player) event.getDamager();
            if(!ItemsInit.hasLore(ChatColor.GRAY + "- 상대를 타격할 시 천둥이 친다.", living)) return;
            LivingEntity entity = (LivingEntity) event.getEntity();
            World world = entity.getWorld();
            if(world.hasStorm()){
                LightningStrike strike = world.strikeLightningEffect(entity.getLocation());
                entity.damage(8, strike);
            }else{
                LightningStrike strike = world.strikeLightningEffect(entity.getLocation());
                entity.damage(4, strike);
            }

        }
    }
}