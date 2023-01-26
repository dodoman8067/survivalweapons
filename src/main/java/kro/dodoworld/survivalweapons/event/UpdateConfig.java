package kro.dodoworld.survivalweapons.event;

import kro.dodoworld.survivalweapons.config.IronPackConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateConfig implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(!IronPackConfig.getIronPackConfig().contains(String.valueOf(event.getPlayer().getUniqueId()))){
            FileConfiguration config = IronPackConfig.getIronPackConfig();
            config.addDefault(String.valueOf(event.getPlayer().getUniqueId()), 0);
            IronPackConfig.saveConfig();
            IronPackConfig.reloadConfig();
        }
    }
}
