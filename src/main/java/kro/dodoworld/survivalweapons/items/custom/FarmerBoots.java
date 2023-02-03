package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class FarmerBoots implements Listener {
    @EventHandler
    public void onWalk(PlayerInteractEvent event){
        if(!event.getAction().equals(Action.PHYSICAL)) return;
        if(event.getClickedBlock() == null) return;
        if(!ItemsInit.isPluginItem("sw_item_farmer_boots", event.getPlayer(), EquipmentSlot.FEET)) return;
        if(!event.getClickedBlock().getType().equals(Material.FARMLAND)) return;
        event.setUseInteractedBlock(Event.Result.DENY);
        event.setUseItemInHand(Event.Result.ALLOW);
    }
}
