package kro.dodoworld.survivalweapons.craft;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class BeginnerToolsCraft implements Listener {

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    @EventHandler
    public void onCraft(CraftItemEvent event){
        if(!event.getCurrentItem().equals(ItemsInit.MysteryBeginnerTool)) return;
        int itemType = random.nextInt(0,6);
        if(itemType == 1){
            event.setCurrentItem(new ItemStack(ItemsInit.BeginnerAxe));
        }
        if(itemType == 2){
            event.setCurrentItem(new ItemStack(ItemsInit.BeginnerPickaxe));
        }
        if(itemType == 3){
            event.setCurrentItem(new ItemStack(ItemsInit.BeginnerShovel));
        }
        if(itemType == 4){
            event.setCurrentItem(new ItemStack(ItemsInit.BeginnerHoe));
        }
        if(itemType == 5){
            event.setCurrentItem(new ItemStack(ItemsInit.BeginnerHoe));
        }
    }
}
