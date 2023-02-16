package kro.dodoworld.survivalweapons.util.item;

import kro.dodoworld.survivalweapons.config.BloodLustConfig;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
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
        checkItem(new ItemStack(ItemsInit.PandoraBox), event.getInventory(), getPandoraBox());
        checkItem(new ItemStack(ItemsInit.PureBlood), event.getInventory(), getPureBlood());
        checkItem(new ItemStack(ItemsInit.ThrowableTNT), event.getInventory(), getThrowableTNT());
        checkItem(new ItemStack(ItemsInit.ValkyrieChestplate), event.getInventory(), getValkyrieChestplate());
        checkItem(new ItemStack(ItemsInit.FarmerBoots), event.getInventory(), getFarmerBoots());
        checkItem(new ItemStack(ItemsInit.DragonSword), event.getInventory(), getDragonSword());
        checkItem(new ItemStack(ItemsInit.DragonBow), event.getInventory(), getDragonBow());
        checkLimitedItem(event);
    }

    private void checkLimitedItem(PrepareItemCraftEvent event){
        FileConfiguration config = BloodLustConfig.getBloodLustConfig();
        if(!(config.getInt(String.valueOf(event.getView().getPlayer().getUniqueId())) >= 1)){
            checkItem(new ItemStack(ItemsInit.BloodLust), event.getInventory(), getBloodLust());
        }
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


    private HashMap<Integer, ItemStack> getDragonSword(){
        HashMap<Integer, ItemStack> dragonSwordRecipe = new HashMap<>();

        dragonSwordRecipe.put(0, new ItemStack(Material.BLAZE_POWDER));
        dragonSwordRecipe.put(1, new ItemStack(ItemsInit.DragonSoul));
        dragonSwordRecipe.put(2, new ItemStack(Material.BLAZE_POWDER));
        dragonSwordRecipe.put(3, new ItemStack(Material.END_CRYSTAL));
        dragonSwordRecipe.put(4, new ItemStack(ItemsInit.DragonSoul));
        dragonSwordRecipe.put(5, new ItemStack(Material.END_CRYSTAL));
        dragonSwordRecipe.put(6, new ItemStack(Material.OBSIDIAN));
        dragonSwordRecipe.put(7, new ItemStack(Material.DIAMOND_SWORD));
        dragonSwordRecipe.put(8, new ItemStack(Material.OBSIDIAN));

        return dragonSwordRecipe;
    }

    private HashMap<Integer, ItemStack> getDragonBow(){
        HashMap<Integer, ItemStack> dragonBowRecipe = new HashMap<>();
        dragonBowRecipe.put(1, new ItemStack(Material.END_CRYSTAL));
        dragonBowRecipe.put(2, new ItemStack(Material.STRING));
        dragonBowRecipe.put(3, new ItemStack(ItemsInit.DragonSoul));
        dragonBowRecipe.put(5, new ItemStack(Material.STRING));
        dragonBowRecipe.put(7, new ItemStack(Material.END_CRYSTAL));
        dragonBowRecipe.put(8, new ItemStack(Material.STRING));

        return dragonBowRecipe;
    }
    private HashMap<Integer, ItemStack> getPureBlood(){
        HashMap<Integer, ItemStack> pureBloodRecipe = new HashMap<>();

        pureBloodRecipe.put(1, new ItemStack(Material.GOLDEN_APPLE));
        pureBloodRecipe.put(3, new ItemStack(Material.GOLDEN_APPLE));
        pureBloodRecipe.put(4, new ItemStack(ItemsInit.ZombieBlood));
        pureBloodRecipe.put(5, new ItemStack(Material.GOLDEN_APPLE));
        pureBloodRecipe.put(7, new ItemStack(Material.GOLDEN_APPLE));

        return pureBloodRecipe;
    }

    private HashMap<Integer, ItemStack> getBloodLust(){
        HashMap<Integer, ItemStack> bloodLustRecipe = new HashMap<>();

        bloodLustRecipe.put(0, new ItemStack(ItemsInit.VampireFang));
        bloodLustRecipe.put(1, new ItemStack(ItemsInit.PureBlood));
        bloodLustRecipe.put(2, new ItemStack(ItemsInit.VampireFang));
        bloodLustRecipe.put(3, new ItemStack(ItemsInit.VampireFang));
        bloodLustRecipe.put(4, new ItemStack(ItemsInit.PureBlood));
        bloodLustRecipe.put(5, new ItemStack(ItemsInit.VampireFang));
        bloodLustRecipe.put(6, new ItemStack(ItemsInit.VampireFang));
        bloodLustRecipe.put(7, new ItemStack(Material.DIAMOND_SWORD));
        bloodLustRecipe.put(8, new ItemStack(ItemsInit.VampireFang));

        return bloodLustRecipe;
    }

    private HashMap<Integer, ItemStack> getPandoraBox(){
        HashMap<Integer, ItemStack> pandoraBoxRecipe = new HashMap<>();

        pandoraBoxRecipe.put(0, new ItemStack(Material.DIAMOND_BLOCK));
        pandoraBoxRecipe.put(1, new ItemStack(Material.TOTEM_OF_UNDYING));
        pandoraBoxRecipe.put(2, new ItemStack(Material.DIAMOND_BLOCK));
        pandoraBoxRecipe.put(3, new ItemStack(Material.GOLD_BLOCK));
        pandoraBoxRecipe.put(4, new ItemStack(Material.CHEST));
        pandoraBoxRecipe.put(5, new ItemStack(Material.GOLD_BLOCK));
        pandoraBoxRecipe.put(6, new ItemStack(Material.EMERALD_BLOCK));
        pandoraBoxRecipe.put(7, new ItemStack(Material.TOTEM_OF_UNDYING));
        pandoraBoxRecipe.put(8, new ItemStack(Material.EMERALD_BLOCK));

        return pandoraBoxRecipe;
    }

    private HashMap<Integer, ItemStack> getThrowableTNT(){
        HashMap<Integer, ItemStack> throwableTNTRecipe = new HashMap<>();

        throwableTNTRecipe.put(1, new ItemStack(Material.BLAZE_POWDER));
        throwableTNTRecipe.put(3, new ItemStack(Material.ARROW));
        throwableTNTRecipe.put(4, new ItemStack(Material.TNT));
        throwableTNTRecipe.put(5, new ItemStack(Material.ARROW));
        throwableTNTRecipe.put(7, new ItemStack(Material.BLAZE_POWDER));

        return throwableTNTRecipe;
    }

    private HashMap<Integer, ItemStack> getValkyrieChestplate(){
        HashMap<Integer, ItemStack> valkyrieChestplate = new HashMap<>();

        valkyrieChestplate.put(0, new ItemStack(Material.NETHERITE_BLOCK));
        valkyrieChestplate.put(2, new ItemStack(Material.NETHERITE_BLOCK));
        valkyrieChestplate.put(3, new ItemStack(Material.DIAMOND_BLOCK));
        valkyrieChestplate.put(4, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
        valkyrieChestplate.put(5, new ItemStack(Material.DIAMOND_BLOCK));
        valkyrieChestplate.put(6, new ItemStack(Material.BLAZE_ROD));
        valkyrieChestplate.put(7, new ItemStack(Material.DIAMOND_CHESTPLATE));
        valkyrieChestplate.put(8, new ItemStack(Material.BLAZE_ROD));

        return valkyrieChestplate;
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
