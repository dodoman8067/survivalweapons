package kro.dodoworld.survivalweapons.features;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class ObtainThunderBottleMethod implements Listener {
    @EventHandler
    public void onInteract(EntityDeathEvent event){
        if(event.getEntity().getKiller() instanceof LightningStrike){
            event.getEntity().getLocation().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(ItemsInit.ThunderBottle));
        }
    }
}
