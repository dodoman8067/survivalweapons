package kro.dodoworld.survivalweapons.util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CoolDown {
    public enum CoolDownType{
        GIANT_SWORD,
        GOLEM_SWORD
    }
    public static HashMap<UUID, Double> GiantSwordCoolDown;
    public static HashMap<UUID, Double> GolemSwordCoolDown;

    public static void setUpCooldown(){
        GiantSwordCoolDown = new HashMap<>();
        GolemSwordCoolDown = new HashMap<>();
    }

    public static void setCooldown(Player player, int seconds, CoolDownType type){
        if(type.equals(CoolDownType.GIANT_SWORD)){
            double delay = System.currentTimeMillis() + (seconds * 1000);
            GiantSwordCoolDown.put(player.getUniqueId(), delay);
        }
        if(type.equals(CoolDownType.GOLEM_SWORD)){
            double delay = System.currentTimeMillis() + (seconds * 1000);
            GolemSwordCoolDown.put(player.getUniqueId(), delay);
        }
    }

    public static boolean checkCooldown(Player player, CoolDownType type){
        if(type.equals(CoolDownType.GIANT_SWORD)){
            return !GiantSwordCoolDown.containsKey(player.getUniqueId()) || GiantSwordCoolDown.get(player.getUniqueId()) <= System.currentTimeMillis();
        }
        if(type.equals(CoolDownType.GOLEM_SWORD)){
            return !GolemSwordCoolDown.containsKey(player.getUniqueId()) || GolemSwordCoolDown.get(player.getUniqueId()) <= System.currentTimeMillis();
        }
        return false;
    }
}