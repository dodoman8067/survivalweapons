package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.concurrent.ThreadLocalRandom;

@Deprecated
public class NuclearBomb implements Listener {

    private static Survivalweapons plugin;
    private static ThreadLocalRandom rnd = ThreadLocalRandom.current();

    public NuclearBomb(Survivalweapons plugin){
        NuclearBomb.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(!ItemsInit.hasLore(ChatColor.LIGHT_PURPLE + "폭☆8", player)) return;
        if(!event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        Location loc = player.getLocation();
        event.getItem().setAmount(event.getItem().getAmount() - 1);
        for(Player title : Bukkit.getOnlinePlayers()){
            title.sendTitle("§4§l" + player.getName() + " (이)가 핵폭탄을 사용했습니다!", "§c늦기 전에 멀리 떨어지세요!", 10, 100, 20);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            explode(loc);
            killAll(loc);
        }, 200L);
    }

    private static void explode(Location loc){
        World world = loc.getWorld();

        for(int i = 0; i<=75; i++){
            Bukkit.getLogger().info("a");
            int killRange = rnd.nextInt(-50,150);
            world.strikeLightning(loc.add(killRange, killRange, killRange));
            world.createExplosion(loc.add(killRange, killRange, killRange), 4L, true, true);
        }
    }

    private static void killAll(Location loc){
        World world = loc.getWorld();
        for(Entity entity : world.getNearbyEntities(loc, 150, 150, 150)){
            if(entity instanceof LivingEntity){
                ((LivingEntity) entity).damage(1000);
            }
        }
    }
}