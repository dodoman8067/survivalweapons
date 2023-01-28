package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class StoneSnowBall implements Listener {
    @EventHandler
    public void onHit(ProjectileHitEvent event){
        if(event.getHitEntity() == null) return;
        if(!(event.getHitEntity() instanceof LivingEntity)) return;
        if(!(event.getEntity().getShooter() instanceof Player)) return;
        if(!(event.getEntity() instanceof Snowball)) return;
        if(!event.getEntity().getScoreboardTags().contains("sbw_stonesnowball")) return;

        LivingEntity entity = (LivingEntity) event.getHitEntity();
        Player player = (Player) event.getEntity().getShooter();

        entity.damage(2, player);
    }

    @EventHandler
    public void onThrow(ProjectileLaunchEvent event){
        if(!(event.getEntity().getShooter() instanceof Player)) return;
        if(!(event.getEntity() instanceof Snowball)) return;
        Player player = (Player) event.getEntity().getShooter();
        if(!ItemsInit.hasLore(ChatColor.RED + "눈덩이 대미지 + 2", player)) return;
        event.getEntity().addScoreboardTag("sbw_stonesnowball");
    }
}
