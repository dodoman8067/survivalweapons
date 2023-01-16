package kro.dodoworld.survivalweapons.items.custom;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
/**
 * Listener for item Exodus
 */
public class Exodus implements Listener {

    /**
     * Apply item's ability
     */
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        if(!hasLoreOnHelmet(ChatColor.GREEN + "상대를 때릴 시 재생 II 효과 +3초를 준다.", player)) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 90, 1, false));
    }

    /**
     * Returns true when player has Exodus on head
     */
    private static boolean hasLoreOnHelmet(String lore, Player player){
        try{
            return player.getInventory().getItem(EquipmentSlot.HEAD).getItemMeta() != null && player.getInventory().getItem(EquipmentSlot.HEAD).getItemMeta().getLore() != null
                    && player.getInventory().getItem(EquipmentSlot.HEAD).getItemMeta().getLore().contains(lore);
        }catch (NullPointerException e){
            return false;
        }
    }
}
