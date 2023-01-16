package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Listener for item Bloodlust
 */
public class BloodLust implements Listener {

    /**
     * When Player kills another Player with Bloodlust
     */
    @EventHandler
    public void onKill(EntityDeathEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        if(event.getEntity().getKiller() == null) return;
        Player player = event.getEntity().getKiller();
        if(!ItemsInit.hasLore(ChatColor.RED + "플레이어들을 죽일 수록 이 검은 더 강력해진다.", player)) return;
        ItemStack stack = player.getInventory().getItemInMainHand();
        ItemMeta meta = stack.getItemMeta();
        List<String> newLore = meta.getLore();
        int killCount = (getDamage(meta) + 1);
        newLore.set(8, ChatColor.AQUA + "킬 수 : " + ChatColor.YELLOW + killCount);
        meta.addEnchant(Enchantment.DAMAGE_ALL, getSharpLevel(meta), true);


        meta.setLore(newLore);
        stack.setItemMeta(meta);
    }

    /**
     * Get kill count from lore
     */
    private static int getDamage(ItemMeta meta){
        String dmgLore = meta.getLore().get(8);
        List<String> loreSplit = new ArrayList<>(Arrays.asList(dmgLore.split(" ")));
        String finalKills = loreSplit.get(3);
        finalKills = finalKills.replaceAll("§e", "");

        return (Integer.parseInt(finalKills));
    }

    /**
     * Get applied Sharpness level based on kill count
     */
    private static int getSharpLevel(ItemMeta meta){
        int returnValue = 1;
        int killCount = getDamage(meta) + 1;

        if(killCount >= 1){
            returnValue = 2;
        }
        if(killCount >= 2){
            returnValue = 3;
        }
        if(killCount >= 4){
            returnValue = 4;
        }
        if(killCount >= 7){
            returnValue = 5;
        }
        if(killCount >= 10){
            returnValue = 6;
        }
        if(killCount >= 20){
            returnValue = 7;
        }
        return returnValue;
    }
}