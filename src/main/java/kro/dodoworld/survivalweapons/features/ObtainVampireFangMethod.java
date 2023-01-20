package kro.dodoworld.survivalweapons.features;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class ObtainVampireFangMethod implements Listener {
    @EventHandler
    public void onDeath(EntityDeathEvent event){
        Bukkit.getLogger().info("1");
        if(event.getEntity().getKiller() != null && event.getEntity() instanceof Bat) {
            Bukkit.getLogger().info("2");
            Bat bat = (Bat) event.getEntity();
            if (!(bat.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE))) return;
            Bukkit.getLogger().info("3");
            if (!(bat.getKiller().getInventory().getItemInMainHand().equals(new ItemStack(Material.BOW)))) return;
            Bukkit.getLogger().info("4");
            double chance = Math.random() * 100;
            if (chance <= 80) {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(ItemsInit.VampireFang));
                event.getEntity().getKiller().sendMessage(ChatColor.GOLD + "" + ChatColor.GOLD + ChatColor.BOLD + "희귀 드랍!" + ChatColor.RED + " Vampire Fang" + ChatColor.GRAY + " x1");
            }
        }
    }
}