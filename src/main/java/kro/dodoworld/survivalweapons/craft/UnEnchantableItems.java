package kro.dodoworld.survivalweapons.craft;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public class UnEnchantableItems implements Listener {
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event){
        if(hasLore(event)){
            event.setResult(null);
        }
    }

    @EventHandler
    public void onToolSmith(InventoryClickEvent event){
        if(isGrindStone(event)){
            if(hasLoreOnGrindStone(event)){
                event.setCancelled(true);
                event.setResult(Event.Result.DENY);
                event.setCurrentItem(new ItemStack(Material.AIR));
            }
        }
    }

    private static boolean isGrindStone(InventoryClickEvent event){
        try{
            return event.getClickedInventory().getType().equals(InventoryType.GRINDSTONE) && event.getSlotType().equals(InventoryType.SlotType.RESULT);
        }catch (NullPointerException e){
            return false;
        }
    }

    private static boolean hasLore(PrepareAnvilEvent event){
        try{
            return event.getInventory().getItem(0).getItemMeta() != null && event.getInventory().getItem(0).getItemMeta().getLore() != null &&
                    event.getInventory().getItem(0).getItemMeta().getLore().contains(ChatColor.GRAY + "이 아이템은 마법 부여가 불가능합니다!") ||
                    event.getInventory().getItem(1).getItemMeta() != null && event.getInventory().getItem(1).getItemMeta().getLore() != null &&
                            event.getInventory().getItem(1).getItemMeta().getLore().contains(ChatColor.GRAY + "이 아이템은 마법 부여가 불가능합니다!");
        }catch (NullPointerException e){
            return false;
        }
    }

    private static boolean hasLoreOnGrindStone(InventoryClickEvent event){
        try{
            return event.getClickedInventory().getItem(2).getItemMeta() != null && event.getClickedInventory().getItem(2).getItemMeta().getLore() != null
                    && event.getClickedInventory().getItem(2).getItemMeta().getLore().contains(ChatColor.GRAY + "이 아이템은 마법 부여가 불가능합니다!");
        }catch (NullPointerException e){
            return false;
        }
    }
}
