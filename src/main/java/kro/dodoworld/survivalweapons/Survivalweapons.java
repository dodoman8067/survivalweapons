package kro.dodoworld.survivalweapons;

import kro.dodoworld.survivalweapons.commands.SwItem;
import kro.dodoworld.survivalweapons.commands.tab.SurvivalweaponsTabCompleter;
import kro.dodoworld.survivalweapons.config.IronPackConfig;
import kro.dodoworld.survivalweapons.craft.*;
import kro.dodoworld.survivalweapons.event.UpdateConfig;
import kro.dodoworld.survivalweapons.features.AgroEnderman;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import kro.dodoworld.survivalweapons.items.custom.*;
import kro.dodoworld.survivalweapons.util.CoolDown;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Survivalweapons extends JavaPlugin {

    //Current branch is 1.18 (Sbe Edition)

    private final Logger logger = getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
        long itemMs = System.currentTimeMillis();
        logger.info("Loading Items...");
        ItemsInit items = new ItemsInit(this);
        items.init();
        Anduril.registerAnduril(this);
        ValkyrieChestplate.registerValkyrieChestplate(this);
        logger.info("Loading Items Took " + (System.currentTimeMillis() - itemMs) + "ms");
        long configMs = System.currentTimeMillis();
        logger.info("Loading Configs...");
        CoolDown.setUpCooldown();
        IronPackConfig.init();
        IronPackConfig.getIronPackConfig().options().copyDefaults(true);
        IronPackConfig.saveConfig();
        logger.info("Loading Config Took " + (System.currentTimeMillis() - configMs) + "ms");

        logger.info("Loading Listeners...");
        long eventMs = System.currentTimeMillis();
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
        getServer().getPluginManager().registerEvents(new BloodLust(), this);
        getServer().getPluginManager().registerEvents(new UnPlaceableBlocks(), this);
        getServer().getPluginManager().registerEvents(new PandoraBox(), this);
        getServer().getPluginManager().registerEvents(new TimeWarpPearl(this), this);
        getServer().getPluginManager().registerEvents(new StoneSnowBall(), this);
        getServer().getPluginManager().registerEvents(new ThrowableTNT(), this);
        getServer().getPluginManager().registerEvents(new GolemSword(), this);
        getServer().getPluginManager().registerEvents(new DragonBow(), this);
        getServer().getPluginManager().registerEvents(new WaterMelonAxe(), this);
        logger.info("Loading Listeners Took " + (System.currentTimeMillis() - eventMs) + "ms");

        long commandMs = System.currentTimeMillis();
        logger.info("Loading Commands...");
        getCommand("sbwitem").setExecutor(new SwItem());
        getCommand("sbwitem").setTabCompleter(new SurvivalweaponsTabCompleter());
        logger.info("Loading Commands Took " + (System.currentTimeMillis() - commandMs) + "ms");

        logger.info("Successfully Registered Events, Items and Commands.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Successfully Disabled Events, Items and Commands.");
    }
}
