package kro.dodoworld.survivalweapons.craft;

import kro.dodoworld.survivalweapons.config.BloodLustConfig;
import kro.dodoworld.survivalweapons.config.ExodusConfig;
import kro.dodoworld.survivalweapons.config.IronPackConfig;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class LimitedItemCraft implements Listener {
    @EventHandler
    public void onCraft(CraftItemEvent event){
        if(event.getCurrentItem().equals(ItemsInit.Exodus)){
            FileConfiguration config = ExodusConfig.getExodusConfig();
            if(config.getInt(String.valueOf(event.getWhoClicked().getUniqueId())) != 0){
                event.getWhoClicked().sendMessage(ChatColor.RED + "이 아이템은 더 이상 제작이 불가능합니다!");
                event.setCurrentItem(new ItemStack(Material.AIR));
                event.setCancelled(true);
            }else{
                if(event.isShiftClick()){
                    event.setCurrentItem(null);
                    event.setCancelled(true);
                    return;
                }
                config.set(String.valueOf(event.getWhoClicked().getUniqueId()), 1);
                ExodusConfig.saveConfig();
                ExodusConfig.reloadConfig();
            }
        }

        if(event.getCurrentItem().equals(ItemsInit.IronPack)){
            FileConfiguration config = IronPackConfig.getIronPackConfig();
            if(config.getInt(String.valueOf(event.getWhoClicked().getUniqueId())) >= 3){
                event.getWhoClicked().sendMessage(ChatColor.RED + "이 아이템은 더 이상 제작이 불가능합니다!");
                event.setCurrentItem(new ItemStack(Material.AIR));
                event.setCancelled(true);
            }else{
                if(event.isShiftClick()){
                    event.setCurrentItem(null);
                    event.setCancelled(true);
                    return;
                }
                config.set(String.valueOf(event.getWhoClicked().getUniqueId()), (config.getInt(String.valueOf(event.getWhoClicked().getUniqueId())) + 1));
                IronPackConfig.saveConfig();
                IronPackConfig.reloadConfig();
            }
        }
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event){
        if(event.getRecipe() == null) return;
        if(event.getRecipe().getResult() == null) return;
        if(event.getRecipe().getResult().equals(new ItemStack(ItemsInit.Exodus))){
            FileConfiguration config = ExodusConfig.getExodusConfig();
            if(config.getInt(String.valueOf(event.getView().getPlayer().getUniqueId())) != 0){
                event.getInventory().setResult(null);
            }
        }
        if(event.getRecipe().getResult().equals(new ItemStack(ItemsInit.IronPack))){
            FileConfiguration config = IronPackConfig.getIronPackConfig();
            if(config.getInt(String.valueOf(event.getView().getPlayer().getUniqueId())) >= 3){
                event.getInventory().setResult(null);
            }
        }
    }
}
