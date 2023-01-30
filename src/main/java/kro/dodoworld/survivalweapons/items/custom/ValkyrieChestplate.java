package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.Survivalweapons;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ValkyrieChestplate {
    public static void registerValkyrieChestplate(Survivalweapons plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (hasLoreOnChestplate(ChatColor.BLUE + "+ 영구 힘 II", player)) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2, 1, true, false, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2, 1, true, false, true));
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    private static boolean hasLoreOnChestplate(String lore, Player player) {
        try {
            return player.getInventory().getItem(EquipmentSlot.CHEST).getItemMeta() != null && player.getInventory().getItem(EquipmentSlot.CHEST).getItemMeta().getLore() != null
                    && player.getInventory().getItem(EquipmentSlot.CHEST).getItemMeta().getLore().contains(lore);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
