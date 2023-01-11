package kro.dodoworld.survivalweapons.features;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Material;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class ObtainThunderBottleMethod implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event){
        if(!event.getPlayer().getInventory().getItemInMainHand().equals(new ItemStack(Material.GLASS_BOTTLE))) return;
        if(!(event.getRightClicked() instanceof LightningStrike)) return;
        ItemStack glassBottle = event.getPlayer().getInventory().getItemInMainHand();
        glassBottle.setAmount(glassBottle.getAmount() - 1);
        event.getPlayer().getInventory().addItem(new ItemStack(ItemsInit.ThunderBottle));
    }
}
