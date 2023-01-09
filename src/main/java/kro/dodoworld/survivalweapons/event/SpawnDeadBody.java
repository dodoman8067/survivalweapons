package kro.dodoworld.survivalweapons.event;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class SpawnDeadBody implements Listener {

    private static Survivalweapons plugin;

    public SpawnDeadBody(Survivalweapons plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event) throws Exception {
        NPC npc = new NPC(event.getEntity().getLocation(), event.getEntity().getDisplayName());
        npc.setGameMode(event.getEntity(), NPC.GameMode.CREATIVE);
        NPC.MetaData metaData = npc.getMetaData();
        metaData.setPose(NPC.Pose.SLEEPING);
        npc.setMetaData(metaData);
        npc.setSkin(NPC.SkinTextures.getByUsername(plugin, event.getEntity().getDisplayName()).getSync());
        npc.spawnNPC(event.getEntity());
    }
}
