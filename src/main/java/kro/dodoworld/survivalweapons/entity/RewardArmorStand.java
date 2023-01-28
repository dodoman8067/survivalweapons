package kro.dodoworld.survivalweapons.entity;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class RewardArmorStand implements Listener {
    public static void createArmorStand(Location loc, ItemStack stack){
        ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class);
        ArmorStand nameStand = stand.getLocation().getWorld().spawn(stand.getLocation().add(0, 1.3,0), ArmorStand.class);
        nameStand.setGravity(false);
        nameStand.setInvulnerable(true);
        nameStand.setVisible(false);
        nameStand.setInvisible(true);
        nameStand.setMarker(true);
        nameStand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.ADDING_OR_CHANGING);
        nameStand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
        nameStand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.ADDING_OR_CHANGING);
        nameStand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
        nameStand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.ADDING_OR_CHANGING);
        nameStand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
        nameStand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.ADDING_OR_CHANGING);
        nameStand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
        nameStand.addScoreboardTag("sw_stand_rewardstand");
        nameStand.setCustomNameVisible(true);
        stand.setVisible(false);
        stand.setSmall(true);
        stand.setInvisible(true);
        stand.setInvulnerable(true);
        stand.setMarker(true);
        stand.setAI(false);
        stand.setGravity(false);
        stand.setArms(false);
        stand.getEquipment().setHelmet(stack);
        if(stack.getItemMeta().hasDisplayName()){
            nameStand.setCustomName(stack.getItemMeta().getDisplayName());
        }else{
            nameStand.setCustomName(stack.getType().name().toString());
        }
        stand.addScoreboardTag("sw_stand_rewardstand");
        stand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.ADDING_OR_CHANGING);
        stand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
        stand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.ADDING_OR_CHANGING);
        stand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
        stand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.ADDING_OR_CHANGING);
        stand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
        stand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.ADDING_OR_CHANGING);
        stand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        for(Entity entity : player.getNearbyEntities(0.5, 1.5, 0.5)){
            if(!(entity instanceof ArmorStand)) return;
            if(!entity.getScoreboardTags().contains("sw_stand_rewardstand")) return;
            ArmorStand stand = (ArmorStand) entity;
            if(stand.getEquipment().getHelmet() == null) return;
            if(player.getInventory().firstEmpty() == -1){
                player.getWorld().dropItemNaturally(stand.getLocation(), stand.getEquipment().getHelmet());
            }else{
                player.getInventory().addItem(new ItemStack(stand.getEquipment().getHelmet()));
            }
            stand.remove();
        }
    }
}

