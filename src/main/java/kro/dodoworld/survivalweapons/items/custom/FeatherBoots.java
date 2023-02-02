package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class FeatherBoots implements Listener {
    @EventHandler
    public void onFallDamage(EntityDamageEvent event){
        if(!event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) return;
        if(!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        if(ItemsInit.isPluginItem("sw_item_feather_boots", player, EquipmentSlot.FEET)){
            ItemStack stack = player.getInventory().getItem(EquipmentSlot.FEET);
            Damageable meta = (Damageable) stack.getItemMeta();
            if(!(stack.getType().getMaxDurability() < meta.getDamage())){
                event.setCancelled(true);
                meta.setDamage(meta.getDamage() + ((int) event.getFinalDamage() / 2));
                stack.setItemMeta(meta);
            }else{
                stack.setItemMeta(meta);
                stack.setAmount(stack.getAmount() - 1);
            }


        }
    }
}
