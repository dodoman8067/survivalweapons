package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import kro.dodoworld.survivalweapons.util.CoolDown;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class DragonBow implements Listener {
    @EventHandler
    public void onLanch(ProjectileLaunchEvent event){
        if(!(event.getEntity().getShooter() instanceof Player)) return;
        if(!(event.getEntity() instanceof Arrow)) return;
        Player player = (Player) event.getEntity().getShooter();
        if(!ItemsInit.hasLore(ChatColor.GRAY + "- 드래곤의 영혼이 깃들어 있는 전설적인 활이다", player)) return;
        Arrow arrow = (Arrow) event.getEntity();
        if(CoolDown.checkCooldown(player, CoolDown.CoolDownType.DRAGON_BOW)){
            arrow.addScoreboardTag("sw_arrow_dragon_bow_skill");
        }else{
            arrow.addScoreboardTag("sw_arrow_dragon_bow");
        }
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event){
        if(!(event.getEntity() instanceof Arrow)) return;
        if(!(event.getEntity().getShooter() instanceof Player)) return;
        if(!(event.getHitEntity() instanceof Enderman)) return;
        Player player = (Player) event.getEntity().getShooter();
        Arrow arrow = (Arrow) event.getEntity();
        if(event.getHitEntity() != null && event.getHitEntity() instanceof Enderman){
            if(arrow.getScoreboardTags().contains("sw_arrow_dragon_bow")) {
                event.setCancelled(true);
                Enderman enderman = (Enderman) event.getHitEntity();
                enderman.damage(arrow.getDamage(), player);
                for(Entity entity : arrow.getNearbyEntities(3, 3, 3)){
                    if(entity instanceof LivingEntity && entity != player){
                        ((LivingEntity) entity).damage(arrow.getDamage() / 2);
                    }
                }
                arrow.setColor(Color.fromRGB(166, 27, 168));
                arrow.remove();
            }
            if(arrow.getScoreboardTags().contains("sw_arrow_dragon_bow_skill")){
                event.setCancelled(true);
                arrow.setDamage(arrow.getDamage() * 3);
                Enderman enderman = (Enderman) event.getHitEntity();
                enderman.damage(arrow.getDamage(), arrow);
                enderman.getWorld().strikeLightningEffect(enderman.getLocation());
                for(Entity entity : arrow.getNearbyEntities(10, 10, 10)){
                    if(entity instanceof LivingEntity && entity != player){
                        ((LivingEntity) entity).damage(arrow.getDamage() / 2);
                    }
                }
                arrow.setColor(Color.fromRGB(166, 27, 168));
                arrow.remove();
                CoolDown.setCooldown(player, 10, CoolDown.CoolDownType.DRAGON_BOW);
            }
        }
    }


    @EventHandler
    public void onHitEntity(ProjectileHitEvent event){
        if(!(event.getEntity() instanceof Arrow)) return;
        if(!(event.getEntity().getShooter() instanceof Player)) return;
        if(event.getEntity() instanceof Enderman) return;
        Player player = (Player) event.getEntity().getShooter();
        Arrow arrow = (Arrow) event.getEntity();
        if(event.getHitEntity() != null){
            if(arrow.getScoreboardTags().contains("sw_arrow_dragon_bow")) {
                for(Entity entity : arrow.getNearbyEntities(3, 3, 3)){
                    if(entity instanceof LivingEntity && entity != player){
                        ((LivingEntity) entity).damage(arrow.getDamage() / 2);
                    }
                }
                arrow.setColor(Color.fromRGB(166, 27, 168));
            }
            if(arrow.getScoreboardTags().contains("sw_arrow_dragon_bow_skill")){
                event.getEntity().getWorld().strikeLightningEffect(event.getHitEntity().getLocation());
                arrow.setDamage(arrow.getDamage() * 3);
                for(Entity entity : arrow.getNearbyEntities(10, 10, 10)){
                    if(entity instanceof LivingEntity && entity != player){
                        ((LivingEntity) entity).damage(arrow.getDamage() / 2);
                    }
                }
                arrow.setColor(Color.fromRGB(166, 27, 168));
                CoolDown.setCooldown(player, 10, CoolDown.CoolDownType.DRAGON_BOW);
            }
        }
        if(event.getHitEntity() == null){
            if(!(arrow.getScoreboardTags().contains("sw_arrow_dragon_bow_skill") || arrow.getScoreboardTags().contains("sw_arrow_dragon_bow"))) return;
            arrow.setColor(Color.fromRGB(166, 27, 168));
            for(Entity entity : arrow.getNearbyEntities(3, 3, 3)){
                if(entity instanceof LivingEntity && entity != player){
                    ((LivingEntity) entity).damage(arrow.getDamage() / 2);
                }
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        if(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if(!ItemsInit.hasLore(ChatColor.GRAY + "- 드래곤의 영혼이 깃들어 있는 전설적인 활이다", event.getPlayer())) return;

            if(CoolDown.checkCooldown(event.getPlayer(), CoolDown.CoolDownType.DRAGON_BOW)){
                event.getPlayer().sendMessage(ChatColor.GREEN + "능력이 준비되었습니다!");
            }else{
                event.getPlayer().sendMessage(ChatColor.RED + "능력이 준비되지 않았습니다!");
            }
        }
    }
}
