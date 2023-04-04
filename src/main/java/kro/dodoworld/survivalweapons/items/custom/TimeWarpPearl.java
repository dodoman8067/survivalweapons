package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TimeWarpPearl implements Listener {

    private Location location;
    private Survivalweapons plugin;

    public TimeWarpPearl(Survivalweapons plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onThrow(ProjectileLaunchEvent event){
        if(!(event.getEntity().getShooter() instanceof Player)) return;
        Player player = (Player) event.getEntity().getShooter();
        if(!ItemsInit.isPluginItem("sw_item_time_warp_pearl", player)) return;
        event.getEntity().addScoreboardTag("sw_timewarp");
        location = player.getLocation();
    }

    @EventHandler
    public void onTeleport(ProjectileHitEvent event){
        if(!(event.getEntity().getShooter() instanceof Player)) return;
        if(!(event.getEntity() instanceof EnderPearl)) return;
        if(!event.getEntity().getScoreboardTags().contains("sw_timewarp")) return;
        Player player = (Player) event.getEntity().getShooter();
        player.sendMessage(ChatColor.GREEN + "5초 후 원위치로 돌아옵니다!");

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        }, 100L);
    }
}
