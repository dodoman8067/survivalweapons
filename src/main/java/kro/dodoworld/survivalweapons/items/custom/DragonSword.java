package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.World;
import org.bukkit.Particle;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.concurrent.ThreadLocalRandom;

public class DragonSword implements Listener {

    private static Survivalweapons plugin;

    public DragonSword(Survivalweapons plugin){
        DragonSword.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        if(!(event.getEntity() instanceof LivingEntity)) return;

        Player player = (Player) event.getDamager();

        LivingEntity target = (LivingEntity) event.getEntity();
        World world = target.getWorld();

        if(!ItemsInit.isPluginItem("sw_item_dragon_sword", player)) return;

        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        int chance = rnd.nextInt(0,2);

        if(chance == 1){
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () ->
                    target.damage(event.getFinalDamage() / 2), 15L);
            world.spawnParticle(Particle.SPELL_WITCH, target.getLocation(), 25, 0.4, 0.5, 0.4);
            world.spawnParticle(Particle.DRAGON_BREATH, target.getLocation(), 10, 0.3, 0.2, 0.3);
            world.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, SoundCategory.PLAYERS, 15, 10);
        }
    }
}