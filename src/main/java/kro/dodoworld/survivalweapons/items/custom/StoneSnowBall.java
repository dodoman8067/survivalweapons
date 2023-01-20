package kro.dodoworld.survivalweapons.items.custom;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class StoneSnowBall implements Listener {
    @EventHandler
    public void onHit(ProjectileHitEvent event){
        if(event.getHitEntity() == null) return;
        if(!(event.getHitEntity() instanceof LivingEntity)) return;
        if(!(event.getEntity().getShooter() instanceof Player)) return;

        LivingEntity entity = (LivingEntity) event.getHitEntity();
        Player player = (Player) event.getEntity().getShooter();

        entity.damage(1, player);
    }
}
