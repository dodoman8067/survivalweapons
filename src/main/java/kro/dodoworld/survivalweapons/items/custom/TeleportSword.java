package kro.dodoworld.survivalweapons.items.custom;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;

public class TeleportSword implements Listener {
    /*        if(event.getHand() == null || !event.getHand().equals(EquipmentSlot.HAND)) return;
        if(event.getItem() == null) return;
        if(!event.getItem().getType().equals(Material.DIAMOND)) return;

     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getHand() == null || !event.getHand().equals(EquipmentSlot.HAND)) return;
        if(event.getItem() == null) return;
        if(!event.getItem().getType().equals(Material.DIAMOND)) return;
        if (event.getAction().toString().contains("RIGHT_CLICK")) {
            Location playerLoc = event.getPlayer().getLocation();
            Vector direction = playerLoc.getDirection().normalize(); // Normalize the direction vector
            Location teleportLocation = null;

            for (int distance = 1; distance <= 8; distance++) {
                Vector checkVector = direction.clone().multiply(distance); // Clone and multiply for each check
                Location checkLocation = playerLoc.clone().add(checkVector);

                if (checkLocation.getBlock().getType().isAir() || checkLocation.getBlock().getType().isTransparent()) {
                    // If it's air, update the potential teleport location
                    teleportLocation = checkLocation;
                } else {
                    // Found a non-air block, stop the loop
                    break;
                }
            }

            // If a teleport location was found, and it's different from the original location
            if (teleportLocation != null && !teleportLocation.equals(playerLoc)) {
                Location loc = teleportLocation.add(0, 0.5, 0);
                if(loc.getBlock().getType().isAir() || loc.getBlock().getType().isTransparent()){
                    event.getPlayer().teleport(teleportLocation.add(0, 0.5, 0)); // Adjust Y to prevent embedding in the ground
                }
                event.getPlayer().sendMessage("Teleported successfully!");
            } else {
                Location loc = event.getPlayer().getLocation().add(0, 0.5, 0);
                if(loc.getBlock().getType().isAir() || loc.getBlock().getType().isTransparent()){
                    event.getPlayer().teleport(loc); // Adjust Y to prevent embedding in the ground
                }
                event.getPlayer().sendMessage("No valid location found for teleportation.");
            }
        }
    }
}
