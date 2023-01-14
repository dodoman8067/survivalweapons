package kro.dodoworld.survivalweapons.loot.custom;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class VampireFangDrop implements LootTable {
    @Override
    public Collection<ItemStack> populateLoot(Random random, LootContext context) {
        int loot = context.getLootingModifier();
        double chance = loot * .01;

        final List<ItemStack> items = new ArrayList<>();
        if(random.nextDouble() <= (.50 + chance)) {

            //int amount = random.nextInt(3);
            //items.add(new ItemStack(Material.AIR, amount == 0 ? 1 : amount));
            items.add(new ItemStack(ItemsInit.VampireFang));
        }
        return items;
    }

    @Override
    public void fillInventory(Inventory inventory, Random random, LootContext context) {

    }

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(JavaPlugin.getProvidingPlugin(Survivalweapons.class), "vampire_fang_from_bat");
    }
}
