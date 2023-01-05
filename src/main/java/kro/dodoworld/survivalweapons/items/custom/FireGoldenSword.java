package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class FireGoldenSword implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        if(event.getEntity() instanceof LivingEntity){
            Player living = (Player) event.getDamager();
            if(!ItemsInit.hasLore(ChatColor.AQUA + "상대가 불에 타고 있을시 "+ ChatColor.GREEN + "+5" + ChatColor.AQUA + "대미지", living)) return;
            LivingEntity entity = (LivingEntity) event.getEntity();
            if(entity.getFireTicks() >= 0){
                event.setDamage(event.getDamage() + 5);
            }
            if(entity.getWorld().getName().endsWith("_nether")){
                event.setDamage(event.getFinalDamage() + (event.getFinalDamage() * 0.2));
            }
        }
    }
}
