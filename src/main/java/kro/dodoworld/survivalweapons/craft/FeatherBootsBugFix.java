package kro.dodoworld.survivalweapons.craft;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class FeatherBootsBugFix implements Listener {
    @EventHandler
    public void onCraft(CraftItemEvent event){
        if(event.getCurrentItem().equals(new ItemStack(ItemsInit.DiamondFeatherBoots))){
            ItemStack stack = event.getInventory().getItem(5);
            Damageable meta = (Damageable) stack.getItemMeta();
            ItemStack result = event.getCurrentItem();
            Damageable resultItemMeta = (Damageable) result.getItemMeta();
            resultItemMeta.setDamage(meta.getDamage());
            result.setItemMeta(resultItemMeta);
        }

        if(event.getCurrentItem().equals(new ItemStack(ItemsInit.IronFeatherBoots))){
            ItemStack stack = event.getInventory().getItem(5);
            Damageable meta = (Damageable) stack.getItemMeta();
            ItemStack result = event.getCurrentItem();
            Damageable resultItemMeta = (Damageable) result.getItemMeta();
            resultItemMeta.setDamage(meta.getDamage());
            result.setItemMeta(resultItemMeta);
        }
    }
}
