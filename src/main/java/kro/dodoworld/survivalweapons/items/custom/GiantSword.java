package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import kro.dodoworld.survivalweapons.util.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class GiantSword implements Listener {
    private static Survivalweapons plugin;

    public GiantSword(Survivalweapons plugin){
        GiantSword.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(ItemsInit.hasLore(ChatColor.GREEN + "대미지를 주며 하늘로 날린다.", event.getPlayer())){
            if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)){

            // Checks player's item is Giant's Sword

                if(Cooldown.checkCooldown(event.getPlayer())){
                    Player player = event.getPlayer();

                    // Summons giant
                    Giant giant = player.getLocation().getWorld().spawn(player.getLocation(), Giant.class);
                    giant.setInvisible(true);
                    giant.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
                    giant.getEquipment().setItemInMainHandDropChance(0);
                    giant.setAI(false);
                    giant.setInvulnerable(true);
                    giant.setGravity(false);
                    giant.setCanPickupItems(false);
                    giant.setCustomName("Dinnerbone");
                    giant.setCustomNameVisible(false);

                    // Damage entities
                    for(Entity entity : giant.getNearbyEntities(6,6,6)){
                        if(entity instanceof LivingEntity){
                            if(entity != player){
                                LivingEntity livingEntity = (LivingEntity) entity;
                                livingEntity.setVelocity(new Vector(0,1,0));
                                livingEntity.damage(8 + (livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.4), player);
                            }
                        }
                    }

                    // Removes giant after 100 ticks (5 seconds)
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, giant::remove, 100L);
                    Cooldown.setCooldown(player, 30);
                }else{
                    event.getPlayer().sendMessage(ChatColor.RED + "이 아이템의 능력은 아직 쿨다운에 있습니다!");
                }
            }
        }
    }
}