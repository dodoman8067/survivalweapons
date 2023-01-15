package kro.dodoworld.survivalweapons.util;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemStackCraft implements Listener {
    @EventHandler
    public void itemCraft(PrepareItemCraftEvent event){
        if(event.getInventory().getMatrix().length < 9) return;
        checkItem(new ItemStack(ItemsInit.LightingSword), event.getInventory(), getLightingAxeRecipe());
        checkItem(new ItemStack(ItemsInit.ZombieBlood), event.getInventory(), getZombieBlood());
    }

    private HashMap<Integer, ItemStack> getLightingAxeRecipe(){
        HashMap<Integer, ItemStack> lightingAxeRecipe = new HashMap<>();

        lightingAxeRecipe.put(0, new ItemStack(ItemsInit.LightingBottle));
        lightingAxeRecipe.put(1, new ItemStack(Material.TNT));
        lightingAxeRecipe.put(2, new ItemStack(ItemsInit.LightingBottle));
        lightingAxeRecipe.put(3, new ItemStack(ItemsInit.LightingBottle));

        lightingAxeRecipe.put(4, new ItemStack(Material.TNT));

        lightingAxeRecipe.put(5, new ItemStack(ItemsInit.LightingBottle));
        lightingAxeRecipe.put(6, new ItemStack(ItemsInit.LightingBottle));
        lightingAxeRecipe.put(7, new ItemStack(Material.DIAMOND_AXE));
        lightingAxeRecipe.put(8, new ItemStack(ItemsInit.LightingBottle));

        return lightingAxeRecipe;
    }

    private HashMap<Integer, ItemStack> getZombieBlood(){
        HashMap<Integer, ItemStack> zombieBloodRecipe = new HashMap<>();

        zombieBloodRecipe.put(1, new ItemStack(Material.ZOMBIE_HEAD));
        zombieBloodRecipe.put(3, new ItemStack(Material.ROTTEN_FLESH));
        zombieBloodRecipe.put(4, new ItemStack(Material.GLASS_BOTTLE));
        zombieBloodRecipe.put(5, new ItemStack(Material.ROTTEN_FLESH));
        zombieBloodRecipe.put(7, new ItemStack(Material.FERMENTED_SPIDER_EYE));

        return zombieBloodRecipe;
    }

    private void checkItem(ItemStack result, CraftingInventory inv, HashMap<Integer, ItemStack> ingredients){
        ItemStack[] matrix = inv.getMatrix();
        for(int i = 0; i < 9; i++){
            if(ingredients.containsKey(i)){
                if(matrix[i] == null || !matrix[i].equals(ingredients.get(i))){
                    return;
                }
            }else{
                if(matrix[i] != null){
                    return;
                }
            }
        }
        inv.setResult(result);
    }
}
