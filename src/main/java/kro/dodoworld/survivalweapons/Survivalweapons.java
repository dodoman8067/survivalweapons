package kro.dodoworld.survivalweapons;

import kro.dodoworld.survivalweapons.commands.SwItem;
import kro.dodoworld.survivalweapons.commands.tab.SurvivalweaponsTabCompleter;
import kro.dodoworld.survivalweapons.config.ExodusConfig;
import kro.dodoworld.survivalweapons.config.IronPackConfig;
import kro.dodoworld.survivalweapons.craft.BeginnerToolsCraft;
import kro.dodoworld.survivalweapons.craft.FeatherBootsBugFix;
import kro.dodoworld.survivalweapons.craft.LimitedItemCraft;
import kro.dodoworld.survivalweapons.craft.UnEnchantableItems;
import kro.dodoworld.survivalweapons.event.UpdateConfig;
import kro.dodoworld.survivalweapons.features.AgroEnderman;
import kro.dodoworld.survivalweapons.features.ObtainThunderBottleMethod;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import kro.dodoworld.survivalweapons.items.custom.*;
import kro.dodoworld.survivalweapons.util.Cooldown;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Survivalweapons extends JavaPlugin {

    //Current branch is 1.18

    private final Logger logger = getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
        ItemsInit.init();
        Cooldown.setUpCooldown();
        Anduril.registerAnduril(this);
        ExodusConfig.init();
        ExodusConfig.getExodusConfig().options().copyDefaults(true);
        ExodusConfig.saveConfig();
        IronPackConfig.init();
        IronPackConfig.getIronPackConfig().options().copyDefaults(true);
        IronPackConfig.saveConfig();

        getServer().getPluginManager().registerEvents(new FireGoldenSword(), this);
        getServer().getPluginManager().registerEvents(new SelfAttackSword(), this);
        getServer().getPluginManager().registerEvents(new LightingSword(), this);
        getServer().getPluginManager().registerEvents(new GiantSword(this), this);
        getServer().getPluginManager().registerEvents(new MarvelOfThunderStorm(), this);
        getServer().getPluginManager().registerEvents(new DragonSword(this), this);
        getServer().getPluginManager().registerEvents(new Exodus(), this);
        getServer().getPluginManager().registerEvents(new UpdateConfig(), this);
        getServer().getPluginManager().registerEvents(new LimitedItemCraft(), this);
        getServer().getPluginManager().registerEvents(new AgroEnderman(), this);
        getServer().getPluginManager().registerEvents(new BeginnerToolsCraft(), this);
        getServer().getPluginManager().registerEvents(new GoldenHead(), this);
        getServer().getPluginManager().registerEvents(new FeatherBoots(), this);
        getServer().getPluginManager().registerEvents(new FeatherBootsBugFix(), this);
        getServer().getPluginManager().registerEvents(new UnEnchantableItems(), this);
        getServer().getPluginManager().registerEvents(new UnPlaceableBlocks(), this);
        getServer().getPluginManager().registerEvents(new BloodLust(), this);
        getServer().getPluginManager().registerEvents(new ObtainThunderBottleMethod(), this);

        getCommand("switem").setExecutor(new SwItem());
        getCommand("switem").setTabCompleter(new SurvivalweaponsTabCompleter());

        logger.info("Successfully Registered Events, Items and Commands.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Successfully Disabled Events, Items and Commands.");
    }
}
