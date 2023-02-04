package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GoldenHead implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && ItemsInit.isPluginItem("sw_item_golden_head", event.getPlayer())){
            event.setCancelled(true);
            Player player = event.getPlayer();
            try{
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            }catch (NullPointerException e){
                e.printStackTrace();
            }

            addEffect(player);
        }

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) && ItemsInit.isPluginItem("sw_item_golden_head", event.getPlayer())){
            if(!event.getHand().equals(EquipmentSlot.HAND)) return;
            Player player = event.getPlayer();
            try{
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            }catch (NullPointerException e){
                e.printStackTrace();
            }

            addEffect(player);
        }
    }

    private static void addEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 3, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 1, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 2, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0, true));
    }
}