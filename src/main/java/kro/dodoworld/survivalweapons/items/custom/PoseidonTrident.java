package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.NamespacedKey;
import org.bukkit.Color;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Trident;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PoseidonTrident implements Listener {
    private static Survivalweapons plugin;
    private int task;

    public PoseidonTrident(Survivalweapons plugin){
        PoseidonTrident.plugin = plugin;
    }
    public static void registerPoseidonTrident(){
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(player.isInWater()){
                        if(ItemsInit.isPluginItem("sw_item_poseidon_trident", player)){
                            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 2, 0, true));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 2, 0, true));
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }


    @EventHandler
    public void onShoot(ProjectileLaunchEvent event){
        if(!(event.getEntity() instanceof Trident)) return;
        if(!(event.getEntity().getShooter() instanceof Player)) return;
        if(((Trident) event.getEntity()).getItem().hasItemMeta() && ((Trident) event.getEntity()).getItem().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "sw_item_poseidon_trident"), PersistentDataType.STRING)){
            Trident trident = (Trident) event.getEntity();
            trident.addScoreboardTag("sw_entity_poseidon_trident");
            task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                if(trident.isDead()) Bukkit.getScheduler().cancelTask(task);
                trident.getWorld().spawnParticle(Particle.REDSTONE, trident.getLocation().getX(), trident.getLocation().getY(), trident.getLocation().getZ(), 10, new Particle.DustOptions(Color.fromRGB(23, 207, 188), 3));
            }, 0L, 1L);
        }
    }
    @EventHandler
    public void onLand(ProjectileHitEvent event){
        if(!(event.getEntity() instanceof Trident)) return;
        if(!(event.getEntity().getShooter() instanceof Player)) return;
        if(event.getEntity().getScoreboardTags().contains("sw_entity_poseidon_trident")) {
            Bukkit.getScheduler().cancelTask(task);
            for(Entity entity : event.getEntity().getNearbyEntities(20, 20, 20)){
                if(entity instanceof Monster || entity instanceof Slime) {
                    if(((Player) event.getEntity().getShooter()).hasLineOfSight(entity) && !entity.isDead()){
                        Trident trident = event.getEntity().getLocation().getWorld().spawn(event.getEntity().getLocation(), Trident.class);
                        trident.setShooter(event.getEntity().getShooter());
                        trident.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                        trident.setDamage(((Trident) event.getEntity()).getDamage() * 1.5);
                        trident.setVelocity(entity.getLocation().toVector().subtract(trident.getLocation().toVector()).multiply(3));
                        trident.addScoreboardTag("sw_entity_poseidon_sub_trident");
                    }
                }
            }
        }
        if(event.getEntity().getScoreboardTags().contains("sw_entity_poseidon_sub_trident")){
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> event.getEntity().remove(),20L);
        }
    }
}
