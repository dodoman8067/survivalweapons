package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WaterMelonAxe implements Listener {
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        if(!(event.getEntity() instanceof LivingEntity)) return;
        double chance = Math.random() * 100;
        Player player = (Player) event.getDamager();
        LivingEntity target = (LivingEntity) event.getEntity();

        if(ItemsInit.hasLore(ChatColor.GRAY + "- 수박이 합쳐서 단단해진 도끼이다", player)){
            if(chance <= 30){
                target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, true, true));
            }
        }

        if(ItemsInit.hasLore(ChatColor.GRAY + "- 흉포한 수박의 도끼이다", player)){
            if(chance <= 50){
                target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, true, true));
            }
        }

        if(ItemsInit.hasLore(ChatColor.GRAY + "- 전설적인 수박의 집합체 이다", player)){
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0, false, true, true));
        }

        if(ItemsInit.hasLore(ChatColor.GRAY + "- 초월한 수박의 원성으로 이루어져 있다", player)){
            if(chance <= 35){
                target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1, false, true, true));
            }
        }
    }
}
