package kro.dodoworld.survivalweapons.event;

import kro.dodoworld.survivalweapons.config.BloodLustConfig;
import kro.dodoworld.survivalweapons.config.ExodusConfig;
import kro.dodoworld.survivalweapons.config.IronPackConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateConfig implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(!ExodusConfig.getExodusConfig().contains(String.valueOf(event.getPlayer().getUniqueId()))){
            FileConfiguration config = ExodusConfig.getExodusConfig();
            config.addDefault(String.valueOf(event.getPlayer().getUniqueId()), 0);
            ExodusConfig.saveConfig();
            ExodusConfig.reloadConfig();
        }

        if(!IronPackConfig.getIronPackConfig().contains(String.valueOf(event.getPlayer().getUniqueId()))){
            FileConfiguration config = IronPackConfig.getIronPackConfig();
            config.addDefault(String.valueOf(event.getPlayer().getUniqueId()), 0);
            IronPackConfig.saveConfig();
            IronPackConfig.reloadConfig();
        }

        if(!BloodLustConfig.getBloodLustConfig().contains(String.valueOf(event.getPlayer().getUniqueId()))){
            FileConfiguration config = BloodLustConfig.getBloodLustConfig();
            config.addDefault(String.valueOf(event.getPlayer().getUniqueId()), 0);
            BloodLustConfig.saveConfig();
            BloodLustConfig.reloadConfig();
        }
    }
}
