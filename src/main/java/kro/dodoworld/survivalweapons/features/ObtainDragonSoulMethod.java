package kro.dodoworld.survivalweapons.features;

import kro.dodoworld.survivalweapons.entity.RewardArmorStand;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class ObtainDragonSoulMethod implements Listener {
    @EventHandler
    public void onDeath(EntityDeathEvent event){
        if(event.getEntity().getKiller() instanceof Player && event.getEntity() instanceof EnderDragon) {
            EnderDragon dragon = (EnderDragon) event.getEntity();
            if(dragon.getDragonBattle() == null) return;
            double pos = Math.random() * 10;
            RewardArmorStand.createArmorStand(new Location(dragon.getWorld(), pos, dragon.getDragonBattle().getEndPortalLocation().add(0, 5, 0).getY(), pos), new ItemStack(ItemsInit.DragonSoul));
        }
    }
}
