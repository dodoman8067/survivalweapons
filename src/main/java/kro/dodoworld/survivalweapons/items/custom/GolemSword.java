package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import kro.dodoworld.survivalweapons.util.CoolDown;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GolemSword implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if(!ItemsInit.hasLore(ChatColor.GREEN + "우클릭 시 떨어지는 모루가 스폰된다.", event.getPlayer())) return;
            if(CoolDown.checkCooldown(event.getPlayer(), CoolDown.CoolDownType.GOLEM_SWORD)){
                for(Entity entity : event.getPlayer().getNearbyEntities(120, 120, 120)){
                    if(entity instanceof LivingEntity && entity != event.getPlayer()){
                        LivingEntity entity1 = (LivingEntity) entity;
                        FallingBlock anvil = event.getPlayer().getWorld().spawnFallingBlock(entity1.getLocation().add(0, 16, 0), Material.ANVIL, (byte) 0);
                        anvil.setDropItem(false);
                        anvil.setHurtEntities(true);
                        anvil.addScoreboardTag("sw_golem_anvil");
                    }
                }
                CoolDown.setCooldown(event.getPlayer(), 240, CoolDown.CoolDownType.GOLEM_SWORD);
            }else{
                event.getPlayer().sendMessage(ChatColor.RED + "이 아이템은 쿨다운에 있습니다!");
            }
        }
    }


    @EventHandler
    public void onLand(EntityChangeBlockEvent event){
        if(!(event.getEntity() instanceof FallingBlock)) return;
        if(!event.getEntity().getScoreboardTags().contains("sw_golem_anvil")) return;
        event.setCancelled(true);
        event.getEntity().remove();
        event.getEntity().getWorld().spawnParticle(Particle.LAVA, event.getEntity().getLocation(), 4, null);
        for(Entity entity : event.getEntity().getNearbyEntities(1.5, 1.5, 1.5)){
            if(entity instanceof LivingEntity){
                entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 10, 3);
                ((LivingEntity) entity).damage(20, event.getEntity());
            }
        }
    }
}

