package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Class for Anduril item
 */
public class Anduril {
    /**
     * Check if Player is holding Anduril every 1 tick
     */
    public static void registerAnduril(Survivalweapons plugin){
        new BukkitRunnable(){

            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(ItemsInit.isPluginItem("sw_item_anduril", player)){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2, 0, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2, 0, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2, 0, true));
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}
