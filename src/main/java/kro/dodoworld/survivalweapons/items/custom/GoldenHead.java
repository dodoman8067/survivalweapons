package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GoldenHead implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && ItemsInit.hasLore(ChatColor.BLUE + "재생 IV +20초", event.getPlayer())){
            event.setCancelled(true);
            Player player = event.getPlayer();
            try{
                event.getItem().setAmount(event.getItem().getAmount() - 1);
            }catch (NullPointerException e){
                e.printStackTrace();
            }

            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 3, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 1, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 2, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0, true));
        }

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) && ItemsInit.hasLore(ChatColor.BLUE + "재생 IV +20초", event.getPlayer())){
            Player player = event.getPlayer();
            try{
                event.getItem().setAmount(event.getItem().getAmount() - 1);
            }catch (NullPointerException e){
                e.printStackTrace();
            }

            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 3, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 1, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 2, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0, true));
        }
    }
}
