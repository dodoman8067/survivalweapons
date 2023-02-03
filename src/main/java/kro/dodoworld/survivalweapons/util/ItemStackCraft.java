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
        checkItem(new ItemStack(ItemsInit.FarmerBoots), event.getInventory(), getFarmerBoots());
    }

    private HashMap<Integer, ItemStack> getFarmerBoots(){
        HashMap<Integer, ItemStack> farmerBootsRecipe = new HashMap<>();
        farmerBootsRecipe.put(0, new ItemStack(Material.PUMPKIN));
        farmerBootsRecipe.put(1, new ItemStack(Material.PUMPKIN));
        farmerBootsRecipe.put(2, new ItemStack(Material.PUMPKIN));
        farmerBootsRecipe.put(3, new ItemStack(Material.PUMPKIN));
        farmerBootsRecipe.put(4, new ItemStack(Material.DIAMOND_BOOTS));
        farmerBootsRecipe.put(5, new ItemStack(Material.PUMPKIN));
        farmerBootsRecipe.put(6, new ItemStack(Material.PUMPKIN));
        farmerBootsRecipe.put(7, new ItemStack(Material.PUMPKIN));
        farmerBootsRecipe.put(8, new ItemStack(Material.PUMPKIN));

        return farmerBootsRecipe;
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
