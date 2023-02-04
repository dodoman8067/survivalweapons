package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

public class VampireHelmet {

    public static void registerVampireHelmet(Survivalweapons plugin){
        new BukkitRunnable(){

            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (ItemsInit.isPluginItem("sw_item_vampire_helmet", player, EquipmentSlot.HEAD)) {
                        for(Entity entity : player.getNearbyEntities(10, 10, 10)){
                            if(entity instanceof LivingEntity){
                                ((LivingEntity) entity).damage(2);
                                if(player.getHealth() != player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()){
                                    player.setHealth(player.getHealth() + 2);
                                }else{
                                    player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}
