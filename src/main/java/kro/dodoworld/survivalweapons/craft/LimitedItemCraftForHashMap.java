package kro.dodoworld.survivalweapons.craft;

import kro.dodoworld.survivalweapons.config.BloodLustConfig;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class LimitedItemCraftForHashMap implements Listener {
    @EventHandler
    public void onCraft(InventoryClickEvent event){
        if(event.getClickedInventory() == null) return;
        if(!event.getClickedInventory().getType().equals(InventoryType.WORKBENCH)) return;
        if(!event.getSlotType().equals(InventoryType.SlotType.RESULT)) return;

        if(event.getCurrentItem().equals(new ItemStack(ItemsInit.BloodLust))){
            FileConfiguration config = BloodLustConfig.getBloodLustConfig();
            if(config.getInt(String.valueOf(event.getWhoClicked().getUniqueId())) >= 1){
                event.getWhoClicked().sendMessage(ChatColor.RED + "이 아이템은 더 이상 제작이 불가능합니다!");
                event.setCurrentItem(new ItemStack(Material.AIR));
                event.setCancelled(true);
            }else{
                config.set(String.valueOf(event.getWhoClicked().getUniqueId()), (config.getInt(String.valueOf(event.getWhoClicked().getUniqueId())) + 1));
                BloodLustConfig.saveConfig();
                BloodLustConfig.reloadConfig();
            }
        }
    }
}
