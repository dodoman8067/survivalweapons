package kro.dodoworld.survivalweapons.craft;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class CustomItemRecipe  {



    public static void registerRecipe(Survivalweapons plugin){
        ItemStack stack = new ItemStack(ItemsInit.LightingBottle);
        RecipeChoice custom1Choice = new RecipeChoice.ExactChoice(ItemsInit.LightingBottle); //custom ingredient
        NamespacedKey key = new NamespacedKey(plugin, "lighting_axe");
        ShapedRecipe recipe = new ShapedRecipe(key, ItemsInit.LightingSword); //custom item that will be crafted
        recipe.shape("HYH", "HYH", "HTH");
        recipe.setIngredient('T', Material.DIAMOND_AXE);
        recipe.setIngredient('H', custom1Choice); // usage of the RecipeChoice
        recipe.setIngredient('Y', Material.TNT);
        Bukkit.addRecipe(recipe);
    }
}
