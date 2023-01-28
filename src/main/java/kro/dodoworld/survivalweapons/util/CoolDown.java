package kro.dodoworld.survivalweapons.util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CoolDown {
    public enum CoolDownType{
        GIANT_SWORD,
        GOLEM_SWORD,
        LIGHTING_AXE,
        DRAGON_BOW;
    }
    private static HashMap<UUID, Double> GiantSwordCoolDown;
    private static HashMap<UUID, Double> GolemSwordCoolDown;
    private static HashMap<UUID, Double> LightingAxeCoolDown;
    private static HashMap<UUID, Double> DragonBowCoolDown;

    public static void setUpCooldown(){
        GiantSwordCoolDown = new HashMap<>();
        GolemSwordCoolDown = new HashMap<>();
        LightingAxeCoolDown = new HashMap<>();
        DragonBowCoolDown = new HashMap<>();
    }

    public static void setCooldown(Player player, double seconds, CoolDownType type){
        if(type.equals(CoolDownType.GIANT_SWORD)){
            double delay = System.currentTimeMillis() + (seconds * 1000);
            GiantSwordCoolDown.put(player.getUniqueId(), delay);
        }
        if(type.equals(CoolDownType.GOLEM_SWORD)){
            double delay = System.currentTimeMillis() + (seconds * 1000);
            GolemSwordCoolDown.put(player.getUniqueId(), delay);
        }
        if(type.equals(CoolDownType.LIGHTING_AXE)){
            double delay = System.currentTimeMillis() + (seconds * 1000);
            LightingAxeCoolDown.put(player.getUniqueId(), delay);
        }
        if(type.equals(CoolDownType.DRAGON_BOW)){
            double delay = System.currentTimeMillis() + (seconds * 1000);
            DragonBowCoolDown.put(player.getUniqueId(), delay);
        }
    }

    public static boolean checkCooldown(Player player, CoolDownType type){
        if(type.equals(CoolDownType.GIANT_SWORD)){
            return !GiantSwordCoolDown.containsKey(player.getUniqueId()) || GiantSwordCoolDown.get(player.getUniqueId()) <= System.currentTimeMillis();
        }
        if(type.equals(CoolDownType.GOLEM_SWORD)){
            return !GolemSwordCoolDown.containsKey(player.getUniqueId()) || GolemSwordCoolDown.get(player.getUniqueId()) <= System.currentTimeMillis();
        }
        if(type.equals(CoolDownType.LIGHTING_AXE)){
            return !LightingAxeCoolDown.containsKey(player.getUniqueId()) || LightingAxeCoolDown.get(player.getUniqueId()) <= System.currentTimeMillis();
        }
        if(type.equals(CoolDownType.DRAGON_BOW)){
            return !DragonBowCoolDown.containsKey(player.getUniqueId()) || DragonBowCoolDown.get(player.getUniqueId()) <= System.currentTimeMillis();
        }

        return false;
    }
}