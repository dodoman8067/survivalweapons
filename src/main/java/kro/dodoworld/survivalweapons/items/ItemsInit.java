package kro.dodoworld.survivalweapons.items;

import kro.dodoworld.survivalweapons.Survivalweapons;
import kro.dodoworld.survivalweapons.util.item.CustomSkulls;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Color;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class for creating custom items
 */
public class ItemsInit {

    @Deprecated
    public static ItemStack FireGoldenSword;
    public static ItemStack LightingSword;
    public static ItemStack GiantSword;
    public static ItemStack MarvelOfThunderStorm;
    public static ItemStack DragonSword;
    public static ItemStack Exodus;
    public static ItemStack SelfAttackSword;
    public static ItemStack NuclearBomb;
    public static ItemStack IronPack;
    public static ItemStack BeginnerSword;
    public static ItemStack BeginnerPickaxe;
    public static ItemStack BeginnerAxe;
    public static ItemStack BeginnerShovel;
    public static ItemStack BeginnerHoe;
    public static ItemStack MysteryBeginnerTool;
    public static ItemStack GoldenHead;
    public static ItemStack Anduril;
    public static ItemStack IronFeatherBoots;
    public static ItemStack DiamondFeatherBoots;
    public static ItemStack BloodLust;
    public static ItemStack LightingBottle;
    public static ItemStack VampireFang;
    public static ItemStack ZombieBlood;
    public static ItemStack PandoraBox;
    public static ItemStack MagicPickaxe;
    public static ItemStack TimeWarpPearl;
    public static ItemStack PureBlood;
    public static ItemStack StoneSnowBall;
    public static ItemStack Excalibur;
    public static ItemStack ThrowableTNT;
    public static ItemStack GolemSword;
    public static ItemStack DragonSoul;
    public static ItemStack DragonBow;
    public static ItemStack ValkyrieChestplate;
    public static ItemStack QuickPickaxe;
    public static ItemStack MinerPickaxe;
    public static ItemStack LapisPickaxe;
    public static ItemStack FarmerBoots;
    public static ItemStack DelicateHoe;
    public static ItemStack VampireHelmet;
    public static ItemStack PoseidonTrident;
    public static ItemStack MonsterZapper;

    private static Survivalweapons plugin;

    public ItemsInit(Survivalweapons plugin){
        ItemsInit.plugin = plugin;
    }

    public void init(){
        createFireGoldenSword();
        createGiantSword();
        createMarvelOfThunderStorm();
        createDragonSword();
        createExodus();
        createSelfAttackSword();
        createNuclearBomb();
        createIronPack();
        createBeginnerTools();
        createMysteryBeginnerTool();
        createGoldenHead();
        createAnduril();
        createIronFeatherBoots();
        createDiamondFeatherBoots();
        createBloodLust();
        createLightingBottle();
        createLightingSword();
        createVampireFang();
        createZombieBlood();
        createPandoraBox();
        createMagicPickaxe();
        createTimeWarpPearl();
        createPureBlood();
        createStoneSnowBall();
        createExcalibur();
        createThrowableTNT();
        createGolemSword();
        createDragonSoul();
        createDragonBow();
        createValkyrieChestplate();
        createQuickPickaxe();
        createMinerPickaxe();
        createLapisPickaxe();
        createFarmerBoots();
        createDelicateHoe();
        createVampireHelmet();
        createPoseidonTrident();
        createMonsterZapper();
    }

    private void createFireGoldenSword(){
        ItemStack stack = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Zombie Piglin's Sword");
        meta.addEnchant(Enchantment.DURABILITY, 4, true);
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "????????? ?????? ?????? ????????? "+ ChatColor.GREEN + "+5" + ChatColor.AQUA + "?????????");
        lore.add(ChatColor.AQUA + "????????? ?????? ??? "+ ChatColor.GREEN + "+20%" + ChatColor.AQUA + "?????? ?????????");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        FireGoldenSword = stack;
    }

    private void createBloodLust(){
        ItemStack stack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Bloodlust");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_bloodlust"), PersistentDataType.STRING, "sw_plugin_item_bloodlust");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + "?????????????????? ?????? ?????? ??? ?????? ??? ???????????????.");
        lore.add(ChatColor.GOLD + "1??? : " + "????????????" + ChatColor.DARK_GRAY + " I" + ChatColor.YELLOW + " ??? II");
        lore.add(ChatColor.GOLD + "2??? : " + "????????????" + ChatColor.DARK_GRAY + " II" + ChatColor.YELLOW + " ??? III");
        lore.add(ChatColor.GOLD + "4??? : " + "????????????" + ChatColor.DARK_GRAY + " III" + ChatColor.YELLOW + " ??? IV");
        lore.add(ChatColor.GOLD + "7??? : " + "????????????" + ChatColor.DARK_GRAY + " IV" + ChatColor.YELLOW + " ??? V");
        lore.add(ChatColor.GOLD + "10??? : " + "????????????" + ChatColor.DARK_GRAY + " V" + ChatColor.YELLOW + " ??? VI");
        lore.add(ChatColor.GOLD + "20??? : " + "????????????" + ChatColor.DARK_GRAY + " VI" + ChatColor.YELLOW + " ??? VII");
        lore.add(" ");
        lore.add(ChatColor.AQUA + "??? ??? : " + ChatColor.YELLOW + "0");
        lore.add(ChatColor.GRAY + "??? ???????????? ?????? ????????? ??????????????????!");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        BloodLust = stack;
    }

    private void createMagicPickaxe(){
        ItemStack stack = new ItemStack(Material.DIAMOND_PICKAXE);
        Damageable meta = (Damageable) stack.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN  + "Magic Pickaxe");
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 5, true);
        meta.setDamage(1551);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "??? ???????????? ?????? ????????? ??????????????????!");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_magic_pickaxe"), PersistentDataType.STRING, "sw_plugin_item_magic_pickaxe");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        MagicPickaxe = stack;
    }

    private void createVampireHelmet(){
        ItemStack stack = new ItemStack(Material.NETHERITE_HELMET);
        Damageable meta = (Damageable) stack.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Vampire Helmet");
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "?????? ???????????? ?????? ???????????????.");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_vampire_helmet"), PersistentDataType.STRING, "sw_plugin_item_vampire_helmet");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        VampireHelmet = stack;
    }


    private void createMinerPickaxe(){
        ItemStack stack = new ItemStack(Material.IRON_PICKAXE);
        Damageable meta = (Damageable) stack.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN  + "Miner Pickaxe");
        meta.addEnchant(Enchantment.DIG_SPEED, 2, false);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "?????? ??? ??? ????????? I + 3???");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_miner_pickaxe"), PersistentDataType.STRING, "sw_plugin_item_miner_pickaxe");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        MinerPickaxe = stack;

        ItemStack MinerPickaxe = new ItemStack(ItemsInit.MinerPickaxe);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("miner_pickaxe"), MinerPickaxe);
        recipe.shape("GGG", "ISI", " S ");
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);

        Bukkit.addRecipe(recipe);
    }

    private void createLapisPickaxe(){
        ItemStack stack = new ItemStack(Material.DIAMOND_PICKAXE);
        Damageable meta = (Damageable) stack.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE  + "Lapis Pickaxe");
        meta.addEnchant(Enchantment.DIG_SPEED, 2, false);
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "?????? ????????? x2??? ?????? +50%");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_lapis_pickaxe"), PersistentDataType.STRING, "sw_plugin_item_lapis_pickaxe");
        stack.setItemMeta(meta);

        LapisPickaxe = stack;

        ItemStack LapisPickaxe = new ItemStack(ItemsInit.LapisPickaxe);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("lapis_pickaxe"), LapisPickaxe);
        recipe.shape("LLL", "DSD", " S ");
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);

        Bukkit.addRecipe(recipe);
    }

    private void createFarmerBoots(){
        ItemStack stack = new ItemStack(Material.DIAMOND_BOOTS);
        Damageable meta = (Damageable) stack.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Farmer's Boots");
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "???????????? ????????? ?????????.");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_farmer_boots"), PersistentDataType.STRING, "sw_plugin_item_farmer_boots");
        stack.setItemMeta(meta);

        FarmerBoots = stack;
    }

    private void createExcalibur(){
        ItemStack stack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GREEN  + "Excalibur");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_BLUE + "?????? ????????? - 20%");
        lore.add(ChatColor.DARK_BLUE + "????????? ?????? ????????? + 1");
        lore.add(" ");
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "??? ?????? ?????? ????????? ?????? ??? ????????? ??????.");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_excalibur"), PersistentDataType.STRING, "sw_plugin_item_excalibur");
        stack.setItemMeta(meta);

        Excalibur = stack;
    }

    private void createMonsterZapper(){
        ItemStack stack = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Monster Zapper");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "?????? ??????????????? ?????? ????????? ?????????.");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "??? ???????????? ?????? ????????? ??????????????????!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_monster_zapper"), PersistentDataType.STRING, "sw_plugin_item_monster_zapper");
        stack.setItemMeta(meta);

        MonsterZapper = stack;
    }

    private void createIronFeatherBoots(){
        ItemStack stack = new ItemStack(Material.IRON_BOOTS);
        ItemMeta meta = stack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "????????? ????????? ????????? ???????????? ?????????.");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_feather_boots"), PersistentDataType.STRING, "sw_plugin_item_feather_boots");
        stack.setItemMeta(meta);
        IronFeatherBoots = stack;

        ItemStack IronFeatherBoots = new ItemStack(ItemsInit.IronFeatherBoots);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("feather_falling_iron_boots"), IronFeatherBoots);
        recipe.shape("AFA", "FIF", "AFA");
        recipe.setIngredient('F', Material.FEATHER);
        recipe.setIngredient('I', Material.IRON_BOOTS);

        Bukkit.addRecipe(recipe);
    }

    private void createLightingBottle(){
        ItemStack stack = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Thunder Bottle");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "????????? ?????? ????????? ?????? ??????.");
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "????????? ????????? ????????? ????????? ?????? ??? ????????? ????????? ??????.");
        meta.setColor(Color.fromRGB(250, 252, 78));
        meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 60, 8, false), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 1, 5, false), true);
        meta.setLore(lore);
        stack.setItemMeta(meta);

        LightingBottle = stack;
    }

    private void createZombieBlood(){
        ItemStack stack = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) stack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Zombie's Blood");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "?????????.");
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "????????? ???????????? ??? ??????.");
        meta.setColor(Color.fromRGB(120, 7, 7));
        meta.addCustomEffect(new PotionEffect(PotionEffectType.HUNGER, 2400, 3, false), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0, false), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 2800, 4, false), true);
        meta.setLore(lore);
        stack.setItemMeta(meta);

        ZombieBlood = stack;
    }

    private void createPureBlood(){
        ItemStack stack = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Pure Blood");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "?????? ???....???????");
        meta.setColor(Color.fromRGB(120, 7, 7));
        meta.setLore(lore);
        stack.setItemMeta(meta);

        PureBlood = stack;
    }

    private void createDiamondFeatherBoots(){
        ItemStack stack = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = stack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "????????? ????????? ????????? ???????????? ?????????.");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_feather_boots"), PersistentDataType.STRING, "sw_plugin_item_feather_boots");
        stack.setItemMeta(meta);
        DiamondFeatherBoots = stack;

        ItemStack DiamondFeatherBoots = new ItemStack(ItemsInit.DiamondFeatherBoots);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("feather_falling_diamond_boots"), DiamondFeatherBoots);
        recipe.shape("AFA", "FDF", "AFA");
        recipe.setIngredient('F', Material.FEATHER);
        recipe.setIngredient('D', Material.DIAMOND_BOOTS);

        Bukkit.addRecipe(recipe);
    }

    private void createGolemSword(){
        ItemStack stack = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Golem Sword");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 12, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -3.4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed", -0.15, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier1);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier2);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_golem_sword"), PersistentDataType.STRING, "sw_plugin_item_golem_sword");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "????????? ??? ???????????? ????????? ????????????.");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "??? ???????????? ?????? ????????? ??????????????????!");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        GolemSword = stack;
    }

    private void createStoneSnowBall(){
        ItemStack stack = new ItemStack(Material.SNOWBALL);
        ItemMeta meta = stack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + "????????? ????????? + 2");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_stone_snowball"), PersistentDataType.STRING, "sw_plugin_item_stone_snowball");
        stack.setItemMeta(meta);
        StoneSnowBall = stack;

        ItemStack StoneSnowBallRecipe = new ItemStack(ItemsInit.StoneSnowBall);
        ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft("stone_snowball"), StoneSnowBallRecipe);
        recipe.addIngredient(Material.STONE);
        recipe.addIngredient(Material.SNOWBALL);

        Bukkit.addRecipe(recipe);
    }

    private void createLightingSword(){
        ItemStack stack = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Lighting Axe");
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier1);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_lighting_axe"), PersistentDataType.STRING, "sw_plugin_item_lighting_sword");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "????????? ?????? ??? ????????? ??????.");
        lore.add(ChatColor.AQUA + "?????? ?????? ???????????? ??? ????????? 2???");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        LightingSword = stack;
    }

    private void createGiantSword(){
        ItemStack stack = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Giant's Sword");
        meta.addEnchant(Enchantment.DURABILITY, 4, true);
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 9, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -3.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier1);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_giant_sword"), PersistentDataType.STRING, "sw_plugin_item_giant_sword");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "?????? :" + ChatColor.BOLD + " " + ChatColor.YELLOW + "????????????");
        lore.add(ChatColor.GREEN + "?????? ?????????????????? ?????? 6?????? ????????? 8 + (?????? ?????? ?????? * 0.4) ?????????");
        lore.add(ChatColor.GREEN + "???????????? ?????? ????????? ?????????.");
        lore.add(ChatColor.YELLOW + "????????? :" + ChatColor.AQUA +" 30???");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        GiantSword = stack;
    }

    private void createMarvelOfThunderStorm(){
        ItemStack stack = new ItemStack(Material.HEART_OF_THE_SEA);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "????????? ????????? ??????");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_marvel_of_thunder_storm"), PersistentDataType.STRING, "sw_plugin_item_thunder_storm");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "?????? :" + ChatColor.BOLD + " " + ChatColor.YELLOW + "??????");
        lore.add(ChatColor.GREEN + "????????? ??? ??? ????????? ?????????.");
        lore.add(ChatColor.YELLOW + "?????? ?????? :" + ChatColor.AQUA +" 1???");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        MarvelOfThunderStorm = stack;
    }

    private void createDragonSword(){
        ItemStack stack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Dragon Sword");
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier1);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_dragon_sword"), PersistentDataType.STRING, "sw_plugin_item_dragon_sword");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "????????? ?????? ??? 50% ????????? ?????? ???????????? 50%????????? ?????? ???????????? ??????.");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        DragonSword = stack;
    }

    private void createDragonBow(){
        ItemStack stack = new ItemStack(Material.BOW);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Dragon Bow");
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, false);
        meta.addEnchant(Enchantment.DURABILITY, 3, false);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_dragon_bow"), PersistentDataType.STRING, "sw_plugin_item_dragon_bow");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "" + ChatColor.BOLD + "?????? : " + ChatColor.AQUA + "???????????? ??????");
        lore.add(ChatColor.LIGHT_PURPLE + "??? ????????? ?????? ????????? ??? ???????????? 3?????? ?????????,");
        lore.add(ChatColor.LIGHT_PURPLE + "?????? ?????? ???????????? ?????????.");
        lore.add(" ");
        lore.add(ChatColor.DARK_PURPLE + "??? ?????? ??????????????? ???????????? ??? ??? ????????????!");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        DragonBow = stack;
    }

    private void createExodus(){
        ItemStack stack = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Exodus");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "????????? ?????? ??? ?????? II ?????? +3?????? ??????.");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_exodus"), PersistentDataType.STRING, "sw_plugin_item_exodus");
        stack.setItemMeta(meta);
        Exodus = stack;
        ItemStack ExodusRecipe = new ItemStack(ItemsInit.Exodus);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("exodus"), ExodusRecipe);
        recipe.shape("NEN", "DAD", "   ");
        recipe.setIngredient('N', Material.NETHERITE_BLOCK);
        recipe.setIngredient('E', Material.EMERALD_BLOCK);
        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe.setIngredient('A', Material.ENCHANTED_GOLDEN_APPLE);

        Bukkit.addRecipe(recipe);
    }

    private void createValkyrieChestplate(){
        ItemStack stack = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Valkyrie's Chestplate");
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_valkyrie_chestplate"), PersistentDataType.STRING, "sw_plugin_item_valkyrie_chestplate");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "+ ?????? ??? II");
        lore.add(ChatColor.BLUE + "+ ?????? ?????? II");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        ValkyrieChestplate = stack;
    }

    private void createSelfAttackSword(){
        ItemStack stack = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Self Attacking Sword");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_self_attack_sword"), PersistentDataType.STRING, "sw_plugin_item_self_attack_sword");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "??o\"????????? pvp??? ????????? ????????? ????????? ?????????.\"");
        lore.add(ChatColor.DARK_GRAY + "??o- dodoman");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        SelfAttackSword = stack;
    }

    private void createNuclearBomb(){
        ItemStack stack = new ItemStack(Material.GUNPOWDER);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "Nuclear Bomb");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "??????8");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        NuclearBomb = stack;
    }

    private void createPoseidonTrident(){
        ItemStack stack = new ItemStack(Material.TRIDENT);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Poseidon's Trident");
        List<String> lore = new ArrayList<>();
        lore.add(net.md_5.bungee.api.ChatColor.of(new java.awt.Color(8, 158, 199)) + "+ ?????? ???????????? ?????? I");
        lore.add(net.md_5.bungee.api.ChatColor.of(new java.awt.Color(8, 158, 199)) + "+ ?????? ?????? ?????? I");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_poseidon_trident"), PersistentDataType.STRING, "sw_plugin_item_poseidon_trident");
        meta.addEnchant(Enchantment.LOYALTY, 3, false);
        meta.addEnchant(Enchantment.CHANNELING, 1, false);
        meta.addEnchant(Enchantment.IMPALING, 5, false);
        stack.setItemMeta(meta);
        PoseidonTrident = stack;
    }

    private void createIronPack(){
        ItemStack stack = new ItemStack(Material.IRON_INGOT, 10);
        IronPack = stack;

        ItemStack IronPackRecipe = new ItemStack(ItemsInit.IronPack);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("iron_pack"), IronPackRecipe);
        recipe.shape("III", "ICI", "III");
        recipe.setIngredient('C', Material.COAL);
        recipe.setIngredient('I', Material.RAW_IRON);

        Bukkit.addRecipe(recipe);
    }

    private void createMysteryBeginnerTool(){
        ItemStack stack = new ItemStack(Material.COAL_BLOCK);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "????????? ?????????");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "????????? ??????????????? ????????????" + ChatColor.AQUA + " ?????? ???" + ChatColor.GREEN + " ???????????????.");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        MysteryBeginnerTool = stack;

        ItemStack MysteryBeginnerToolRecipe = new ItemStack(ItemsInit.MysteryBeginnerTool);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("mystery_beginner_tool"), MysteryBeginnerToolRecipe);
        recipe.shape("SSS", "SCS", "SSS");
        recipe.setIngredient('S', Material.COBBLESTONE);
        recipe.setIngredient('C', Material.CHEST);

        Bukkit.addRecipe(recipe);
    }

    private void createAnduril(){
        ItemStack stack = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "And??ril");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 2, false);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_anduril"), PersistentDataType.STRING, "sw_plugin_item_anduril");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "+ ?????? ?????? I");
        lore.add(ChatColor.BLUE + "+ ?????? ?????? I");
        lore.add(ChatColor.BLUE + "+ ?????? ?????? ?????? I");
        lore.add(ChatColor.GRAY + "??? ???????????? ?????? ????????? ??????????????????!");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        Anduril = stack;
    }

    private void createGoldenHead(){
        ItemStack stack = new ItemStack(CustomSkulls.getSkull("http://textures.minecraft.net/texture/4e5b308a1eb5caa97e5fb257b2d9e1861fdef15161d50a1f46f22315f4929"));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Golden Head");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "????????? :");
        lore.add(ChatColor.BLUE + "?????? IV +20???");
        lore.add(ChatColor.BLUE + "?????? II +25???");
        lore.add(ChatColor.BLUE + "?????? III +3???");
        lore.add(ChatColor.BLUE + "?????? ?????? +180???");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_golden_head"), PersistentDataType.STRING, "sw_plugin_item_golden_head");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        GoldenHead = stack;

        ItemStack GoldenHeadRecipe = new ItemStack(ItemsInit.GoldenHead);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("golden_head_from_zombie_recipe"), GoldenHeadRecipe);
        recipe.shape("SSS", "SCS", "SSS");
        recipe.setIngredient('S', Material.GOLD_INGOT);
        recipe.setIngredient('C', Material.ZOMBIE_HEAD);

        Bukkit.addRecipe(recipe);

        ItemStack GoldenHeadRecipe1 = new ItemStack(ItemsInit.GoldenHead);
        ShapedRecipe recipe1 = new ShapedRecipe(NamespacedKey.minecraft("golden_head_from_skeleton_recipe"), GoldenHeadRecipe1);
        recipe1.shape("SSS", "SCS", "SSS");
        recipe1.setIngredient('S', Material.GOLD_INGOT);
        recipe1.setIngredient('C', Material.SKELETON_SKULL);

        Bukkit.addRecipe(recipe1);

        ItemStack GoldenHeadRecipe2 = new ItemStack(ItemsInit.GoldenHead);
        ShapedRecipe recipe2 = new ShapedRecipe(NamespacedKey.minecraft("golden_head_from_creeper_recipe"), GoldenHeadRecipe2);
        recipe2.shape("SSS", "SCS", "SSS");
        recipe2.setIngredient('S', Material.GOLD_INGOT);
        recipe2.setIngredient('C', Material.CREEPER_HEAD);

        Bukkit.addRecipe(recipe2);
    }

    private void createBeginnerTools(){
        ItemStack stack = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Beginner's Sword");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "???????????? ???");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        BeginnerSword = stack;

        ItemStack stack1 = new ItemStack(Material.STONE_AXE);
        ItemMeta meta1 = stack1.getItemMeta();
        meta1.setDisplayName(ChatColor.WHITE + "Beginner's Axe");
        meta1.addEnchant(Enchantment.DIG_SPEED, 2, false);
        meta1.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore1 = new ArrayList<>();
        lore1.add(ChatColor.WHITE + "???????????? ??????");
        meta1.setLore(lore1);
        stack1.setItemMeta(meta1);
        BeginnerAxe = stack1;

        ItemStack stack2 = new ItemStack(Material.STONE_SHOVEL);
        ItemMeta meta2 = stack2.getItemMeta();
        meta2.setDisplayName(ChatColor.WHITE + "Beginner's Shovel");
        meta2.addEnchant(Enchantment.DIG_SPEED, 2, false);
        meta2.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore2 = new ArrayList<>();
        lore2.add(ChatColor.WHITE + "???????????? ???");
        meta2.setLore(lore2);
        stack2.setItemMeta(meta2);
        BeginnerShovel = stack2;

        ItemStack stack3 = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta meta3 = stack3.getItemMeta();
        meta3.setDisplayName(ChatColor.WHITE + "Beginner's Pickaxe");
        meta3.addEnchant(Enchantment.DIG_SPEED, 2, false);
        meta3.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore3 = new ArrayList<>();
        lore3.add(ChatColor.WHITE + "???????????? ?????????");
        meta3.setLore(lore3);
        stack3.setItemMeta(meta3);
        BeginnerPickaxe = stack3;

        ItemStack stack4 = new ItemStack(Material.STONE_HOE);
        ItemMeta meta4 = stack4.getItemMeta();
        meta4.setDisplayName(ChatColor.WHITE + "Beginner's Hoe");
        meta4.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, false);
        meta4.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore4 = new ArrayList<>();
        lore4.add(ChatColor.WHITE + "???????????? ??????");
        meta4.setLore(lore4);
        stack4.setItemMeta(meta4);
        BeginnerHoe = stack4;
    }

    private void createDragonSoul(){
        ItemStack stack = new ItemStack(Material.PURPLE_DYE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "Dragon Soul");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "????????? ????????? ?????????.");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        DragonSoul = stack;
    }

    private void createVampireFang(){
        ItemStack stack = new ItemStack(Material.GHAST_TEAR);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Vampire Fang");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 4, false);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "?????? ????????? ??????????????? ??? ?????? ????????? ??????????????????.");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        VampireFang = stack;
    }

    private void createPandoraBox(){
        ItemStack stack = new ItemStack(CustomSkulls.getSkull("http://textures.minecraft.net/texture/844498a0fe278956e3d04135ef4b1343d0548a7e208c61b1fb6f3b4dbc240da8"));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.MAGIC + ChatColor.BOLD + "A" + ChatColor.RESET + ChatColor.GOLD + "Pandora's Box" + ChatColor.GOLD + "" + ChatColor.MAGIC + ChatColor.BOLD + "A");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "????????? ??? ??? : ");
        lore.add(" ");
        lore.add(ChatColor.GOLD + "" + ChatColor.MAGIC + "?????? ??????????????????!");
        lore.add(ChatColor.AQUA + "" + ChatColor.MAGIC + "?????? ??? ???????????? ?????? ????????? ????????????..");
        lore.add(ChatColor.AQUA + "" + ChatColor.MAGIC + "?????? ???????????? ??? ????????? ?????? ?????? ?????? ????????????");
        lore.add(ChatColor.AQUA + "" + ChatColor.MAGIC + "?????? ?????? ???????????? ????????????????");
        lore.add(ChatColor.GREEN + "" + ChatColor.MAGIC + "????????? ??? ????????? ?????? ??????????????? ????????????!");
        lore.add(ChatColor.DARK_GREEN + "" + ChatColor.MAGIC + "?????? ????????? ???????????? ?????? ????????? ????????????????");
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "" + ChatColor.MAGIC + "- ?????????");
        lore.add("  ");
        lore.add(ChatColor.GRAY + "??? ???????????? ????????? ??????????????????.");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_pandora_box"), PersistentDataType.STRING, "sw_plugin_item_pandora_box_unplaceable");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        PandoraBox = stack;
    }

    private void createThrowableTNT(){
        ItemStack stack = new ItemStack(Material.TNT);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "????????? TNT");
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_throwable_tnt"), PersistentDataType.STRING, "sw_plugin_item_throwable_tnt");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + "??????????????? TNT??? ?????? ??? ??????.");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "??? ???????????? ????????? ??????????????????.");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stack.setItemMeta(meta);

        ThrowableTNT = stack;
    }

    private void createTimeWarpPearl(){
        ItemStack stack = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Time Warp Pearl");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_time_warp_pearl"), PersistentDataType.STRING, "sw_plugin_item_time_warp_pearl");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "5??? ??? ??? ????????? ?????? ????????? ????????????.");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        TimeWarpPearl = stack;
    }

    private void createQuickPickaxe(){
        ItemStack stack = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta meta = stack.getItemMeta();
        meta.addEnchant(Enchantment.DIG_SPEED, 1, false);
        meta.setDisplayName(ChatColor.GREEN + "Quick Pickaxe");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "?????? ?????????.");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_quick_pickaxe"), PersistentDataType.STRING, "sw_plugin_item_quick_pickaxe");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        QuickPickaxe = stack;

        ItemStack QuickPickaxeRecipe = new ItemStack(ItemsInit.QuickPickaxe);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("quick_pickaxe"), QuickPickaxeRecipe);
        recipe.shape("III", "CSC", " S ");
        recipe.setIngredient('C', Material.CHARCOAL);
        recipe.setIngredient('I', Material.RAW_IRON);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
    }

    private void createDelicateHoe(){
        ItemStack stack = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta meta = stack.getItemMeta();
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2, false);
        meta.setDisplayName(ChatColor.BLUE + "Delicate Hoe");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "??? ?????? ?????? ????????? ???????????? ?????????.");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "sw_item_delicate_hoe"), PersistentDataType.STRING, "sw_plugin_item_delicate_hoe");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        DelicateHoe = stack;

        ItemStack DelicateHoeRecipe = new ItemStack(ItemsInit.DelicateHoe);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("delicate_hoe"), DelicateHoeRecipe);
        recipe.shape("DDO", "RSC", " S ");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('O', Material.OBSERVER);
        recipe.setIngredient('C', Material.CLOCK);
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
    }

    public static boolean hasLore(String lore, Player player){
        return player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lore);
    }

    public static boolean isPluginItem(String key, Player player){
        if(player.getInventory().getItemInMainHand().getItemMeta() == null){
            return false;
        }else{
            return player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, key), PersistentDataType.STRING);
        }
    }

    public static boolean isPluginItem(String key, Player player, EquipmentSlot slot){
        try{
            return player.getInventory().getItem(slot).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, key), PersistentDataType.STRING);
        }catch (NullPointerException e){
            return false;
        }
    }
}