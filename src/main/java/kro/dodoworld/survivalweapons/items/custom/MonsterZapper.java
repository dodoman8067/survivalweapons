package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import kro.dodoworld.survivalweapons.util.code.ParticleUtil;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MonsterZapper implements Listener {

    private final Survivalweapons plugin;
    private final List<String> playerUUIDs;

    public MonsterZapper(Survivalweapons plugin){
        this.plugin = plugin;
        this.playerUUIDs = new ArrayList<>();
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(!ItemsInit.isPluginItem("sw_item_monster_zapper", event.getPlayer())) return;
            if(playerUUIDs.contains(event.getPlayer().getUniqueId().toString())) return;
            Player player = event.getPlayer();
            playerUUIDs.add(player.getUniqueId().toString());

            ArmorStand pickaxe = event.getPlayer().getWorld().spawn(event.getPlayer().getLocation().add(0, 0.5, 0), ArmorStand.class);
            pickaxe.setInvisible(true);
            pickaxe.setVisible(false);
            pickaxe.setInvulnerable(false);
            pickaxe.setGravity(false);
            pickaxe.setBasePlate(false);
            pickaxe.setMarker(true);
            pickaxe.setArms(true);

            pickaxe.getEquipment().setItemInMainHand(player.getInventory().getItemInMainHand());
            pickaxe.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.ADDING_OR_CHANGING);
            pickaxe.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
            pickaxe.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.ADDING_OR_CHANGING);
            pickaxe.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
            pickaxe.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.ADDING_OR_CHANGING);
            pickaxe.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
            pickaxe.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.ADDING_OR_CHANGING);
            pickaxe.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
            pickaxe.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
            pickaxe.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
            pickaxe.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
            pickaxe.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
            pickaxe.addScoreboardTag("sw_entity_remove_when_reload");
            pickaxe.setRightArmPose(new EulerAngle(Math.toRadians(90), Math.toRadians(0), Math.toRadians(0)));

            Location dest = player.getLocation().add(player.getLocation().getDirection().multiply(10));
            Vector vector = dest.subtract(player.getLocation()).toVector();

            new BukkitRunnable(){
                int i = 0;
                final int distance = 60;

                @Override
                public void run() {
                    EulerAngle angle = pickaxe.getRightArmPose();
                    EulerAngle angleNew = angle.add(0.3, 0, 0);
                    pickaxe.setRightArmPose(angleNew);

                    pickaxe.teleport(pickaxe.getLocation().add(vector.normalize()));

                    if(pickaxe.getTargetBlockExact(1) != null && !Objects.requireNonNull(pickaxe.getTargetBlockExact(1)).isPassable()){
                        if(!pickaxe.isDead()){
                            pickaxe.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, pickaxe.getLocation(), 4);
                            pickaxe.getWorld().playSound(pickaxe.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 10, 10);
                            for(Entity entity1 : pickaxe.getNearbyEntities(16, 16, 16)){
                                if(entity1 instanceof LivingEntity && !(entity1 instanceof ArmorStand) && entity1 != player){
                                    if(entity1.getSpawnCategory().equals(SpawnCategory.MONSTER)){
                                        ((LivingEntity) entity1).damage(45, player);
                                        ParticleUtil.spawnLaser(pickaxe.getLocation().add(0, 1, 0), entity1.getLocation().add(0, 1, 0), Color.fromRGB(252, 213, 18));
                                        entity1.getWorld().strikeLightningEffect(entity1.getLocation());
                                        entity1.setFireTicks(200);
                                    }else{
                                        ((LivingEntity) entity1).damage(2, player);
                                        ParticleUtil.spawnLaser(pickaxe.getLocation().add(0, 1, 0), entity1.getLocation().add(0, 1, 0), Color.fromRGB(252, 213, 18));
                                        entity1.getWorld().strikeLightningEffect(entity1.getLocation());
                                        entity1.setFireTicks(20);
                                    }
                                }
                            }
                            playerUUIDs.remove(player.getUniqueId().toString());
                            pickaxe.remove();
                            cancel();
                        }
                    }
                    for (Entity entity : pickaxe.getLocation().getChunk().getEntities()){
                        if(!pickaxe.isDead()){
                            if(pickaxe.getLocation().distanceSquared(entity.getLocation()) <= 2){
                                if(entity != player && entity != pickaxe){
                                    if(entity instanceof LivingEntity livingEntity){
                                        livingEntity.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, livingEntity.getLocation(), 4);
                                        livingEntity.getWorld().playSound(livingEntity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 10, 10);
                                        for(Entity entity1 : livingEntity.getNearbyEntities(16, 16, 16)){
                                            if(entity1 instanceof LivingEntity && !(entity1 instanceof ArmorStand) && entity1 != player){
                                                if(entity1.getSpawnCategory().equals(SpawnCategory.MONSTER)){
                                                    ((LivingEntity) entity1).damage(45, player);
                                                    ParticleUtil.spawnLaser(pickaxe.getLocation().add(0, 1, 0), entity1.getLocation().add(0, 1, 0), Color.fromRGB(252, 213, 18));
                                                    entity1.getWorld().strikeLightningEffect(entity1.getLocation());
                                                    entity1.setFireTicks(200);
                                                }else {
                                                    ((LivingEntity) entity1).damage(2, player);
                                                    ParticleUtil.spawnLaser(pickaxe.getLocation().add(0, 1, 0), entity1.getLocation().add(0, 1, 0), Color.fromRGB(252, 213, 18));
                                                    entity1.getWorld().strikeLightningEffect(entity1.getLocation());
                                                    entity1.setFireTicks(20);
                                                }
                                            }
                                        }
                                        playerUUIDs.remove(player.getUniqueId().toString());
                                        pickaxe.remove();
                                        cancel();
                                    }
                                }
                            }
                        }
                    }
                    if(i > distance){
                        if(!pickaxe.isDead()){
                            pickaxe.remove();
                            playerUUIDs.remove(player.getUniqueId().toString());
                            cancel();
                        }
                    }
                    i++;
                }
            }.runTaskTimer(plugin, 0L, 1L);
        }
    }
}
