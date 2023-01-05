package kro.dodoworld.survivalweapons.features;

import org.bukkit.entity.Drowned;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.concurrent.ThreadLocalRandom;

public class SeaCreatures implements Listener {

    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    @EventHandler
    public void onFish(PlayerFishEvent event){
        if(!event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) return;

        Player player = event.getPlayer();

        int chance = random.nextInt(0,5);
        int mobType = random.nextInt(0,3);

        if(chance == 1){
            if(mobType == 1){
                Drowned drowned = event.getHook().getLocation().getWorld().spawn(event.getHook().getLocation(), Drowned.class);
                drowned.setTarget(player);
                drowned.setPersistent(true);
                drowned.setVelocity(player.getLocation().toVector());
            }
        }
    }
}
