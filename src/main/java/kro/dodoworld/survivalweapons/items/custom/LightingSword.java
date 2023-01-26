package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import kro.dodoworld.survivalweapons.util.CoolDown;
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
            if(!ItemsInit.hasLore(ChatColor.AQUA + "상대를 때릴 시 천둥이 친다.", living)) return;
            if(!CoolDown.checkCooldown(living, CoolDown.CoolDownType.LIGHTING_AXE)) return;
            LivingEntity entity = (LivingEntity) event.getEntity();
            World world = entity.getWorld();
            if(world.hasStorm()){
                LightningStrike strike = world.strikeLightningEffect(entity.getLocation());
                entity.damage(8, strike);
                entity.setFireTicks(200);
                CoolDown.setCooldown(living, 2, CoolDown.CoolDownType.LIGHTING_AXE);
            }else{
                LightningStrike strike = world.strikeLightningEffect(entity.getLocation());
                entity.damage(4, strike);
                entity.setFireTicks(100);
                CoolDown.setCooldown(living, 1.5, CoolDown.CoolDownType.LIGHTING_AXE);
            }
        }
    }
}
