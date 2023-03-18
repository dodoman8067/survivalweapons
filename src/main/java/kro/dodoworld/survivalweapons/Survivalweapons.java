package kro.dodoworld.survivalweapons;

import kro.dodoworld.survivalweapons.commands.SwItem;
import kro.dodoworld.survivalweapons.commands.tab.SurvivalweaponsTabCompleter;
import kro.dodoworld.survivalweapons.config.BloodLustConfig;
import kro.dodoworld.survivalweapons.config.ExodusConfig;
import kro.dodoworld.survivalweapons.config.IronPackConfig;
import kro.dodoworld.survivalweapons.craft.*;
import kro.dodoworld.survivalweapons.entity.RewardArmorStand;
import kro.dodoworld.survivalweapons.event.UpdateConfig;
import kro.dodoworld.survivalweapons.features.AgroEnderman;
import kro.dodoworld.survivalweapons.features.ObtainDragonSoulMethod;
import kro.dodoworld.survivalweapons.features.ObtainThunderBottleMethod;
import kro.dodoworld.survivalweapons.features.ObtainVampireFangMethod;
import kro.dodoworld.survivalweapons.items.ItemsInit;
import kro.dodoworld.survivalweapons.items.custom.*;
import kro.dodoworld.survivalweapons.util.item.CoolDown;
import kro.dodoworld.survivalweapons.util.item.ItemStackCraft;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Survivalweapons extends JavaPlugin {

    //Current branch is 1.18

    private final Logger logger = getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
        long itemMs = System.currentTimeMillis();
        logger.info("Loading items...");
        ItemsInit items = new ItemsInit(this);
        items.init();
        Anduril.registerAnduril(this);
        ValkyrieChestplate.registerValkyrieChestplate(this);
        VampireHelmet.registerVampireHelmet(this);
        new PoseidonTrident(this);
        PoseidonTrident.registerPoseidonTrident();
        logger.info("Loading items took " + (System.currentTimeMillis() - itemMs) + "ms");
        long configMs = System.currentTimeMillis();
        logger.info("Loading configs...");
        CoolDown.setUpCooldown();
        ExodusConfig.init();
        ExodusConfig.getExodusConfig().options().copyDefaults(true);
        ExodusConfig.saveConfig();
        BloodLustConfig.init();
        BloodLustConfig.getBloodLustConfig().options().copyDefaults(true);
        BloodLustConfig.saveConfig();
        IronPackConfig.init();
        IronPackConfig.getIronPackConfig().options().copyDefaults(true);
        IronPackConfig.saveConfig();
        logger.info("Loading config took " + (System.currentTimeMillis() - configMs) + "ms");

        logger.info("Loading listeners...");
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
        getServer().getPluginManager().registerEvents(new ObtainThunderBottleMethod(), this);
        getServer().getPluginManager().registerEvents(new ObtainDragonSoulMethod(), this);
        getServer().getPluginManager().registerEvents(new ItemStackCraft(), this);
        getServer().getPluginManager().registerEvents(new LimitedItemCraftForHashMap(), this);
        getServer().getPluginManager().registerEvents(new ObtainVampireFangMethod(), this);
        getServer().getPluginManager().registerEvents(new PandoraBox(), this);
        getServer().getPluginManager().registerEvents(new TimeWarpPearl(this), this);
        getServer().getPluginManager().registerEvents(new StoneSnowBall(), this);
        getServer().getPluginManager().registerEvents(new ThrowableTNT(), this);
        getServer().getPluginManager().registerEvents(new GolemSword(), this);
        getServer().getPluginManager().registerEvents(new RewardArmorStand(), this);
        getServer().getPluginManager().registerEvents(new DragonBow(), this);
        getServer().getPluginManager().registerEvents(new AbilityPickaxe(), this);
        getServer().getPluginManager().registerEvents(new FarmerBoots(), this);
        getServer().getPluginManager().registerEvents(new PoseidonTrident(this), this);
        getServer().getPluginManager().registerEvents(new MonsterZapper(this), this);
        logger.info("Loading listeners Took " + (System.currentTimeMillis() - eventMs) + "ms");

        long commandMs = System.currentTimeMillis();
        logger.info("Loading commands...");
        getCommand("switem").setExecutor(new SwItem());
        getCommand("switem").setTabCompleter(new SurvivalweaponsTabCompleter());
        logger.info("Loading commands took " + (System.currentTimeMillis() - commandMs) + "ms");

        logger.info("Successfully registered events, items and commands.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Successfully disabled Events, items and commands.");
    }
}
