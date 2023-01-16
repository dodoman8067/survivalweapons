package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Listener for item MarvelOfThunderStorm
 */
public class MarvelOfThunderStorm implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(!ItemsInit.hasLore(ChatColor.GREEN + "우클릭 할 시 뇌우를 부른다.", event.getPlayer())) return;
            if(!event.getPlayer().getWorld().hasStorm()){
                event.getPlayer().sendMessage("§7§o구슬은 뇌우를 부르며 천천히 사라집니다...");
                event.getPlayer().getWorld().setStorm(true);
                event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getItem().getAmount() - 1);
                for(Player player : Bukkit.getOnlinePlayers()){
                    player.sendTitle("§b§l누군가 뇌우를 불렀습니다!", "§f날씨가 뇌우로 바뀌었습니다!", 5, 150, 20);
                }
            }else{
                event.getPlayer().sendMessage("§7§o구슬은 아무런 힘을 발휘하지 못하며 천천히 사라집니다...");
                event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getItem().getAmount() - 1);
                for(Player player : Bukkit.getOnlinePlayers()){
                    player.sendTitle("§b§l누군가 뇌우를 불렀습니다!", "§f하지만 바뀐 것은 없습니다!", 5, 150, 20);
                }
            }
        }
    }
}
