package kro.dodoworld.survivalweapons.items;

import kro.dodoworld.survivalweapons.util.CustomSkulls;
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


    public static void init(){
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
    }

    private static void createFireGoldenSword(){
        ItemStack stack = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Zombie Piglin's Sword");
        meta.addEnchant(Enchantment.DURABILITY, 4, true);
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "상대가 불에 타고 있을시 "+ ChatColor.GREEN + "+5" + ChatColor.AQUA + "대미지");
        lore.add(ChatColor.AQUA + "네더에 있을 시 "+ ChatColor.GREEN + "+20%" + ChatColor.AQUA + "추가 대미지");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        FireGoldenSword = stack;
    }

    private static void createBloodLust(){
        ItemStack stack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Bloodlust");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + "플레이어들을 죽일 수록 이 검은 더 강력해진다.");
        lore.add(ChatColor.GOLD + "1킬 : " + "날카로움" + ChatColor.DARK_GRAY + " I" + ChatColor.YELLOW + " → II");
        lore.add(ChatColor.GOLD + "2킬 : " + "날카로움" + ChatColor.DARK_GRAY + " II" + ChatColor.YELLOW + " → III");
        lore.add(ChatColor.GOLD + "4킬 : " + "날카로움" + ChatColor.DARK_GRAY + " III" + ChatColor.YELLOW + " → IV");
        lore.add(ChatColor.GOLD + "7킬 : " + "날카로움" + ChatColor.DARK_GRAY + " IV" + ChatColor.YELLOW + " → V");
        lore.add(ChatColor.GOLD + "10킬 : " + "날카로움" + ChatColor.DARK_GRAY + " V" + ChatColor.YELLOW + " → VI");
        lore.add(ChatColor.GOLD + "20킬 : " + "날카로움" + ChatColor.DARK_GRAY + " VI" + ChatColor.YELLOW + " → VII");
        lore.add(" ");
        lore.add(ChatColor.AQUA + "킬 수 : " + ChatColor.YELLOW + "0");
        lore.add(ChatColor.GRAY + "이 아이템은 마법 부여가 불가능합니다!");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        BloodLust = stack;
    }

    private static void createMagicPickaxe(){
        ItemStack stack = new ItemStack(Material.DIAMOND_PICKAXE);
        Damageable meta = (Damageable) stack.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN  + "Magic Pickaxe");
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 5, true);
        meta.setDamage(1551);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "이 아이템은 마법 부여가 불가능합니다!");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        MagicPickaxe = stack;
    }

    private static void createExcalibur(){
        ItemStack stack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GREEN  + "Excalibur");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_BLUE + "받는 대미지 - 20%");
        lore.add(ChatColor.DARK_BLUE + "무기의 최종 대미지 + 1");
        lore.add(" ");
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "이 검을 뽑는 자만이 왕이 될 자격이 있다.");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        Excalibur = stack;
    }

    private static void createIronFeatherBoots(){
        ItemStack stack = new ItemStack(Material.IRON_BOOTS);
        ItemMeta meta = stack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "낙사를 방지해 주지만 내구도가 깨진다.");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        IronFeatherBoots = stack;

        ItemStack IronFeatherBoots = new ItemStack(ItemsInit.IronFeatherBoots);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("feather_falling_iron_boots"), IronFeatherBoots);
        recipe.shape("AFA", "FIF", "AFA");
        recipe.setIngredient('F', Material.FEATHER);
        recipe.setIngredient('I', Material.IRON_BOOTS);

        Bukkit.addRecipe(recipe);
    }

    private static void createLightingBottle(){
        ItemStack stack = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Thunder Bottle");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "이것에 대한 정보는 얼마 없다.");
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "하지만 이것은 강력한 무기를 만들 수 있다는 소문이 있다.");
        meta.setColor(Color.fromRGB(250, 252, 78));
        meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 60, 8, false), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 1, 5, false), true);
        meta.setLore(lore);
        stack.setItemMeta(meta);

        LightingBottle = stack;
    }

    private static void createZombieBlood(){
        ItemStack stack = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) stack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Zombie's Blood");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "썩었다.");
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "하지만 되살아날 수 있다.");
        meta.setColor(Color.fromRGB(120, 7, 7));
        meta.addCustomEffect(new PotionEffect(PotionEffectType.HUNGER, 2400, 3, false), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0, false), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 2800, 4, false), true);
        meta.setLore(lore);
        stack.setItemMeta(meta);

        ZombieBlood = stack;
    }

    private static void createPureBlood(){
        ItemStack stack = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Pure Blood");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "그냥 피....일까?");
        meta.setColor(Color.fromRGB(120, 7, 7));
        meta.setLore(lore);
        stack.setItemMeta(meta);

        PureBlood = stack;
    }

    private static void createDiamondFeatherBoots(){
        ItemStack stack = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = stack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "낙사를 방지해 주지만 내구도가 깨진다.");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        DiamondFeatherBoots = stack;

        ItemStack DiamondFeatherBoots = new ItemStack(ItemsInit.DiamondFeatherBoots);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("feather_falling_diamond_boots"), DiamondFeatherBoots);
        recipe.shape("AFA", "FDF", "AFA");
        recipe.setIngredient('F', Material.FEATHER);
        recipe.setIngredient('D', Material.DIAMOND_BOOTS);

        Bukkit.addRecipe(recipe);
    }

    private static void createGolemSword(){
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
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "우클릭 시 떨어지는 모루가 스폰된다.");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "이 아이템은 마법 부여가 불가능합니다!");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        GolemSword = stack;
    }

    private static void createStoneSnowBall(){
        ItemStack stack = new ItemStack(Material.SNOWBALL);
        ItemMeta meta = stack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + "눈덩이 대미지 + 2");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        StoneSnowBall = stack;

        ItemStack StoneSnowBallRecipe = new ItemStack(ItemsInit.StoneSnowBall);
        ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft("stone_snowball"), StoneSnowBallRecipe);
        recipe.addIngredient(Material.STONE);
        recipe.addIngredient(Material.SNOWBALL);

        Bukkit.addRecipe(recipe);
    }

    private static void createLightingSword(){
        ItemStack stack = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Lighting Axe");
        meta.addEnchant(Enchantment.FIRE_ASPECT, 2, false);
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "상대를 때릴 시 천둥이 친다.");
        lore.add(ChatColor.AQUA + "비가 올때 천둥으로 준 대미지 2배");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        LightingSword = stack;
    }

    private static void createGiantSword(){
        ItemStack stack = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Giant's Sword");
        meta.addEnchant(Enchantment.DURABILITY, 4, true);
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 9, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -3.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "능력 :" + ChatColor.BOLD + " " + ChatColor.YELLOW + "내리꽂기");
        lore.add(ChatColor.GREEN + "검을 내리꽂으면서 주위 6블럭 적에게 8 + (적의 최대 체력 * 0.4) 만큼의");
        lore.add(ChatColor.GREEN + "대미지를 주며 하늘로 날린다.");
        lore.add(ChatColor.YELLOW + "쿨다운 :" + ChatColor.AQUA +" 30초");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        GiantSword = stack;
    }

    private static void createMarvelOfThunderStorm(){
        ItemStack stack = new ItemStack(Material.HEART_OF_THE_SEA);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "뇌우를 부르는 구슬");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "능력 :" + ChatColor.BOLD + " " + ChatColor.YELLOW + "뇌우");
        lore.add(ChatColor.GREEN + "우클릭 할 시 뇌우를 부른다.");
        lore.add(ChatColor.YELLOW + "사용 횟수 :" + ChatColor.AQUA +" 1회");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        MarvelOfThunderStorm = stack;
    }

    private static void createDragonSword(){
        ItemStack stack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Dragon Sword");
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "상대를 때릴 시 50% 확률로 기존 대미지의 50%만큼의 추가 대미지를 준다.");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        DragonSword = stack;
    }

    private static void createExodus(){
        ItemStack stack = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Exodus");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "상대를 때릴 시 재생 II 효과 +3초를 준다.");
        meta.setLore(lore);
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

    private static void createSelfAttackSword(){
        ItemStack stack = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Self Attacking Sword");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "§o\"진정한 pvp의 고수는 도구를 탓하지 않는다.\"");
        lore.add(ChatColor.DARK_GRAY + "§o- dodoman");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        SelfAttackSword = stack;
    }

    private static void createNuclearBomb(){
        ItemStack stack = new ItemStack(Material.GUNPOWDER);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "Nuclear Bomb");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "폭☆8");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        NuclearBomb = stack;
    }

    private static void createIronPack(){
        ItemStack stack = new ItemStack(Material.IRON_INGOT, 10);
        IronPack = stack;

        ItemStack IronPackRecipe = new ItemStack(ItemsInit.IronPack);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("iron_pack"), IronPackRecipe);
        recipe.shape("III", "ICI", "III");
        recipe.setIngredient('C', Material.COAL);
        recipe.setIngredient('I', Material.RAW_IRON);

        Bukkit.addRecipe(recipe);
    }

    private static void createMysteryBeginnerTool(){
        ItemStack stack = new ItemStack(Material.COAL_BLOCK);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "숨겨진 아이템");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "당신이 제작하려는 아이템은" + ChatColor.AQUA + " 제작 후" + ChatColor.GREEN + " 공개됩니다.");
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

    private static void createAnduril(){
        ItemStack stack = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Anduril");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 2, false);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "+ 영구 신속 I");
        lore.add(ChatColor.BLUE + "+ 영구 저항 I");
        lore.add(ChatColor.GRAY + "이 아이템은 마법 부여가 불가능합니다!");
        meta.setLore(lore);
        stack.setItemMeta(meta);

        Anduril = stack;
    }

    private static void createGoldenHead(){
        ItemStack stack = new ItemStack(CustomSkulls.getSkull("http://textures.minecraft.net/texture/4e5b308a1eb5caa97e5fb257b2d9e1861fdef15161d50a1f46f22315f4929"));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Golden Head");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "먹을시 :");
        lore.add(ChatColor.BLUE + "재생 IV +20초");
        lore.add(ChatColor.BLUE + "신속 II +25초");
        lore.add(ChatColor.BLUE + "저항 III +3초");
        lore.add(ChatColor.BLUE + "화염 저항 +180초");
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

    private static void createBeginnerTools(){
        ItemStack stack = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Beginner's Sword");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "초보자의 검");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        BeginnerSword = stack;

        ItemStack stack1 = new ItemStack(Material.STONE_AXE);
        ItemMeta meta1 = stack1.getItemMeta();
        meta1.setDisplayName(ChatColor.WHITE + "Beginner's Axe");
        meta1.addEnchant(Enchantment.DIG_SPEED, 2, false);
        meta1.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore1 = new ArrayList<>();
        lore1.add(ChatColor.WHITE + "초보자의 도끼");
        meta1.setLore(lore1);
        stack1.setItemMeta(meta1);
        BeginnerAxe = stack1;

        ItemStack stack2 = new ItemStack(Material.STONE_SHOVEL);
        ItemMeta meta2 = stack2.getItemMeta();
        meta2.setDisplayName(ChatColor.WHITE + "Beginner's Shovel");
        meta2.addEnchant(Enchantment.DIG_SPEED, 2, false);
        meta2.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore2 = new ArrayList<>();
        lore2.add(ChatColor.WHITE + "초보자의 삽");
        meta2.setLore(lore2);
        stack2.setItemMeta(meta2);
        BeginnerShovel = stack2;

        ItemStack stack3 = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta meta3 = stack3.getItemMeta();
        meta3.setDisplayName(ChatColor.WHITE + "Beginner's Pickaxe");
        meta3.addEnchant(Enchantment.DIG_SPEED, 2, false);
        meta3.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore3 = new ArrayList<>();
        lore3.add(ChatColor.WHITE + "초보자의 곡괭이");
        meta3.setLore(lore3);
        stack3.setItemMeta(meta3);
        BeginnerPickaxe = stack3;

        ItemStack stack4 = new ItemStack(Material.STONE_HOE);
        ItemMeta meta4 = stack4.getItemMeta();
        meta4.setDisplayName(ChatColor.WHITE + "Beginner's Hoe");
        meta4.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, false);
        meta4.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore4 = new ArrayList<>();
        lore4.add(ChatColor.WHITE + "초보자의 괭이");
        meta4.setLore(lore4);
        stack4.setItemMeta(meta4);
        BeginnerHoe = stack4;
    }

    private static void createVampireFang(){
        ItemStack stack = new ItemStack(Material.GHAST_TEAR);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Vampire Fang");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 4, false);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "피와 제대로 결합된다면 그 어떤 것보다 날카로워진다.");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        VampireFang = stack;
    }

    private static void createPandoraBox(){
        ItemStack stack = new ItemStack(CustomSkulls.getSkull("http://textures.minecraft.net/texture/844498a0fe278956e3d04135ef4b1343d0548a7e208c61b1fb6f3b4dbc240da8"));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.MAGIC + ChatColor.BOLD + "A" + ChatColor.RESET + ChatColor.GOLD + "Pandora's Box" + ChatColor.GOLD + "" + ChatColor.MAGIC + ChatColor.BOLD + "A");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "상자를 열 시 : ");
        lore.add(" ");
        lore.add(ChatColor.GOLD + "" + ChatColor.MAGIC + "이걸 발견하시다니!");
        lore.add(ChatColor.AQUA + "" + ChatColor.MAGIC + "저도 이 텍스트를 보는 방법을 모르는데..");
        lore.add(ChatColor.AQUA + "" + ChatColor.MAGIC + "아마 설명란에 이 상자를 열면 어떤 일이 일어날지");
        lore.add(ChatColor.AQUA + "" + ChatColor.MAGIC + "적혀 있을 것이라고 기대했었죠?");
        lore.add(ChatColor.GREEN + "" + ChatColor.MAGIC + "하지만 이 상자는 흔한 랜덤박스가 아닙니다!");
        lore.add(ChatColor.DARK_GREEN + "" + ChatColor.MAGIC + "결국 위험을 감수하고 직접 상자를 열어야겠죠?");
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "" + ChatColor.MAGIC + "- 개발자");
        lore.add("  ");
        lore.add(ChatColor.GRAY + "이 아이템은 설치가 불가능합니다.");

        meta.setLore(lore);
        stack.setItemMeta(meta);
        PandoraBox = stack;
    }

    private static void createThrowableTNT(){
        ItemStack stack = new ItemStack(Material.TNT);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "투척용 TNT");
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + "우클릭으로 TNT를 던질 수 있다.");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "이 아이템은 설치가 불가능합니다.");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stack.setItemMeta(meta);

        ThrowableTNT = stack;
    }

    private static void createTimeWarpPearl(){
        ItemStack stack = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Time Warp Pearl");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "5초 뒤 이 진주를 던진 장소로 돌아온다.");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        TimeWarpPearl = stack;
    }

    public static boolean hasLore(String lore, Player player){
        return player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lore);
    }
}