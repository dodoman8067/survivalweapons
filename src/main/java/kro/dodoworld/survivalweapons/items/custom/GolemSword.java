package kro.dodoworld.survivalweapons.items.custom;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GolemSword implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        //TODO : Add item detection
        for(Entity entity : event.getPlayer().getNearbyEntities(10, 10, 10)){
            if(entity instanceof LivingEntity){
                LivingEntity entity1 = (LivingEntity) entity;
                FallingBlock anvil = event.getPlayer().getWorld().spawnFallingBlock(entity1.getLocation().add(0, 16, 0), Material.ANVIL, (byte) 0);
                anvil.setDropItem(false);
                anvil.setHurtEntities(true);
                anvil.addScoreboardTag("sw_golem_anvil");
            }
        }
    }


    @EventHandler
    public void onLand(EntityChangeBlockEvent event){
        if(!(event.getEntity() instanceof FallingBlock)) return;
        if(!event.getEntity().getScoreboardTags().contains("sw_golem_anvil")) return;
        event.setCancelled(true);
        event.getEntity().remove();
    }
}
