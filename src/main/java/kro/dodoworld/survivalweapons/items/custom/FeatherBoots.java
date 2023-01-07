package kro.dodoworld.survivalweapons.items.custom;

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

        if(hasLoreOnBoots(ChatColor.AQUA + "낙사를 방지해 주지만 내구도가 깨진다.", player)){
            event.setCancelled(true);
            ItemStack stack = player.getInventory().getItem(EquipmentSlot.FEET);
            Damageable meta = (Damageable) stack.getItemMeta();
            meta.setDamage(meta.getDamage() + ((int) event.getFinalDamage() / 2));
            stack.setItemMeta(meta);
        }
    }

    private static boolean hasLoreOnBoots(String lore, Player player){
        try{
            return player.getInventory().getItem(EquipmentSlot.FEET).getItemMeta() != null && player.getInventory().getItem(EquipmentSlot.FEET).getItemMeta().getLore() != null
                    && player.getInventory().getItem(EquipmentSlot.FEET).getItemMeta().getLore().contains(lore);
        }catch (NullPointerException e){
            return false;
        }
    }
}
