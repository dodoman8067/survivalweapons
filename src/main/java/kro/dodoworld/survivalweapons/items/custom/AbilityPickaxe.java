package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AbilityPickaxe implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(ItemsInit.isPluginItem("sw_item_miner_pickaxe", event.getPlayer())) return;
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 60, 0, true));
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(ItemsInit.isPluginItem("sw_item_lapis_pickaxe", event.getPlayer())) return;
        int chance = (int) (Math.random() * 100);
        if(chance <= 50){
            if(event.getExpToDrop() <= 0){
                event.setExpToDrop(2);
            }else{
                event.setExpToDrop(event.getExpToDrop() * 2);
            }
        }
    }
}