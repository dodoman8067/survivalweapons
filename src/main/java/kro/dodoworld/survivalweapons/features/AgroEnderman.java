package kro.dodoworld.survivalweapons.features;

import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class AgroEnderman implements Listener {
    @EventHandler
    public void onThrow(PlayerTeleportEvent event){
        if(event.getCause().equals(PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT) || event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)){
            Player player = event.getPlayer();
            for(Entity entity : player.getNearbyEntities(15, 15, 15)){
                if(entity instanceof Enderman){
                    Enderman enderman = (Enderman) entity;
                    enderman.setTarget(player);
                }
            }
        }
    }
}
