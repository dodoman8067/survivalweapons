package kro.dodoworld.survivalweapons.features;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import net.minecraft.world.entity.monster.EntityWitch;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftWitch;

import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;

public class ObtainThunderBottleMethod implements Listener {
    @EventHandler
    public void onDeath(EntityDeathEvent event){
        if(event.getEntity().getKiller() != null && event.getEntity() instanceof Witch){
            if(!event.getEntity().getWorld().hasStorm()) return;
            CraftWitch witch = (CraftWitch) event.getEntity();
            if(!witch.getHandle().n()) return;

            double chance = Math.random();
            if(chance <= 0.15){
                event.getEntity().getWorld().strikeLightningEffect(event.getEntity().getLocation());
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(ItemsInit.LightingBottle));
            }
        }
    }
}
