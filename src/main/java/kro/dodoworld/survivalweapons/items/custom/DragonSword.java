package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.event.PlayerDragonSwordEffectEvent;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
/**
 * Listener for item DragonSword
 */
public class DragonSword implements Listener {

    private static Survivalweapons plugin;

    /**
     * Constructor for DragonSword
     */
    public DragonSword(Survivalweapons plugin){
        DragonSword.plugin = plugin;
    }

    /**
     * Apply DragonSword's effect
     */
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        if(!(event.getEntity() instanceof LivingEntity)) return;

        Player player = (Player) event.getDamager();

        LivingEntity target = (LivingEntity) event.getEntity();
        World world = target.getWorld();

        if(!ItemsInit.isPluginItem("sw_item_dragon_sword", player)) return;

        double chance = Math.random() * 10;

        if(chance <= 5){
            PlayerDragonSwordEffectEvent dragonSwordEffectEvent = new PlayerDragonSwordEffectEvent(player, true, true, true);
            if(dragonSwordEffectEvent.isCancelled()) return;
            if(dragonSwordEffectEvent.shouldDamageEntity()){
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () ->
                        target.damage(event.getFinalDamage() / 2), 15L);
            }
            if(dragonSwordEffectEvent.shouldSpawnParticles()){
                world.spawnParticle(Particle.SPELL_WITCH, target.getLocation(), 25, 0.4, 0.5, 0.4);
                world.spawnParticle(Particle.DRAGON_BREATH, target.getLocation(), 10, 0.3, 0.2, 0.3);
            }
            if(dragonSwordEffectEvent.shouldPlaySound()){
                world.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, SoundCategory.PLAYERS, 15, 10);
            }
        }
    }
}