package kro.dodoworld.survivalweapons.features;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class ObtainDragonSoulMethod implements Listener {
    @EventHandler
    public void onDeath(EntityDeathEvent event){
        if(event.getEntity().getKiller() instanceof Player && event.getEntity() instanceof EnderDragon) {
            EnderDragon dragon = (EnderDragon) event.getEntity();
            if(dragon.getDragonBattle() == null) return;
            Player player = event.getEntity().getKiller();
            if(!dragon.getDragonBattle().hasBeenPreviouslyKilled()){
                for(Player player1 : dragon.getWorld().getPlayers()){
                    if(player1.getInventory().firstEmpty() == -1){
                        player1.getWorld().dropItemNaturally(player1.getLocation(), new ItemStack(ItemsInit.DragonSoul));
                    }else{
                        player1.getInventory().addItem(new ItemStack(ItemsInit.DragonSoul));
                    }
                }
            }else{
                double chance = Math.random() * 100;
                if(chance <= 20){
                    if(player.getInventory().firstEmpty() == -1){
                        player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(ItemsInit.DragonSoul));
                    }else{
                        player.getInventory().addItem(new ItemStack(ItemsInit.DragonSoul));
                    }
                }
            }
        }
    }
}