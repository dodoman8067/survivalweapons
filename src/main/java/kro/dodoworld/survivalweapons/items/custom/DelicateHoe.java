package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DelicateHoe implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(!(event.getBlock().getBlockData() instanceof Ageable plant)) return;
        if(!ItemsInit.isPluginItem("sw_item_delicate_hoe", event.getPlayer())) return;

        if(plant.getAge() != plant.getMaximumAge()){
            event.setCancelled(true);
        }
    }
}
