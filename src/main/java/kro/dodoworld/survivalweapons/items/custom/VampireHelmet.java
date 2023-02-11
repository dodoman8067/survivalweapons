package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import kro.dodoworld.survivalweapons.util.CoolDown;
import kro.dodoworld.survivalweapons.util.ParticleUtil;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Tameable;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

public class VampireHelmet {

    public static void registerVampireHelmet(Survivalweapons plugin){
        new BukkitRunnable(){

            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (ItemsInit.isPluginItem("sw_item_vampire_helmet", player, EquipmentSlot.HEAD)) {
                        if(CoolDown.checkCooldown(player, CoolDown.CoolDownType.VAMPIRE_HELMET) && !player.getNearbyEntities(10, 10, 10).isEmpty()){
                            for(Entity entity : player.getNearbyEntities(10, 10, 10)){
                                if(entity instanceof LivingEntity && entity != player && !(entity instanceof ArmorStand)){
                                    if(!(entity instanceof Tameable && ((Tameable) entity).getOwner() != null && ((Tameable) entity).getOwner().getUniqueId().equals(player.getUniqueId()))){
                                        ((LivingEntity) entity).damage(2);
                                        try{
                                            player.setHealth(player.getHealth() + 2);
                                            ParticleUtil.spawnLaser(player.getLocation().add(0, 1, 0), entity.getLocation(), Color.fromRGB(143, 16, 16));
                                        }catch (IllegalArgumentException e){
                                            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                                            ParticleUtil.spawnLaser(player.getLocation().add(0, 1, 0), entity.getLocation(), Color.fromRGB(143, 16, 16));
                                        }
                                        CoolDown.setCooldown(player, 2, CoolDown.CoolDownType.VAMPIRE_HELMET);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}