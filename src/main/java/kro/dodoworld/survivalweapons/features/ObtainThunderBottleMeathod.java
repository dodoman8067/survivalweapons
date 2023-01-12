package kro.dodoworld.survivalweapons.features;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class ObtainThunderBottleMeathod implements Listener {
    @EventHandler
    public void onDeath(EntityDeathEvent event){
        if(event.getEntity().getKiller() != null && event.getEntity() instanceof Witch){
            if(!event.getEntity().getWorld().hasStorm()) return;
            Witch witch = (Witch) event.getEntity();
            if(!witch.isDrinkingPotion()) return;

            double chance = Math.random();
            if(chance <= 0.15){
                event.getEntity().getWorld().strikeLightningEffect(event.getEntity().getLocation());
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(ItemsInit.LightingBottle));
            }
        }
    }
}
