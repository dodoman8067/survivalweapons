package kro.dodoworld.survivalweapons.features;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ObtainThunderBottleMethod implements Listener {

    private static Survivalweapons plugin;

    public ObtainThunderBottleMethod(Survivalweapons plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onInteract(EntityDeathEvent event){

        if(!event.getEntity().getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING)) return;

        if(event.getEntity().getEquipment().getItemInMainHand() != null && event.getEntity().getEquipment().getItemInMainHand().equals(new ItemStack(Material.GLASS_BOTTLE))){

            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> event.getEntity().getLocation().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(ItemsInit.ThunderBottle)), 10L);
        }

    }
}
