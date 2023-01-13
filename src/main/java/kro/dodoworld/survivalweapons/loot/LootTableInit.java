package kro.dodoworld.survivalweapons.loot;

import kro.dodoworld.survivalweapons.loot.custom.VampireFangDrop;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;

import java.util.Collection;
import java.util.Random;

public class LootTableInit implements Listener {
    @EventHandler
    public void onDeath(EntityDeathEvent event){
        if(event.getEntity().getLastDamageCause() == null || !(event.getEntity().getLastDamageCause().getEntity() instanceof Player)) return;

        final Player player = (Player) event.getEntity().getLastDamageCause().getEntity();

        if(event.getEntity().getType().equals(EntityType.BAT)){
            if(!event.getEntity().getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) return;
            final Collection<ItemStack> items = new VampireFangDrop().populateLoot(new Random(), getVampireFangLoot(event));

            event.getDrops().addAll(items);
        }
    }

    private static LootContext getVampireFangLoot(EntityDeathEvent event){
        Player player = (Player) event.getEntity().getLastDamageCause().getEntity();
        LootContext context = new LootContext.Builder(event.getEntity().getLocation())
                .killer(player)
                .lootedEntity(event.getEntity())
                .build();

        return context;
    }
}
