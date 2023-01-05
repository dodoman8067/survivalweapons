package kro.dodoworld.survivalweapons.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class for creating custom items
 */
public class ItemsInit {

    public static ItemStack FireGoldenSword;
    public static ItemStack LightingSword;
    public static ItemStack SpeedSterBoots;
    public static ItemStack GiantSword;
    public static ItemStack MarvelOfThunderStorm;
    public static ItemStack DragonSword;
    public static ItemStack Exodus;
    public static ItemStack SelfAttackSword;
    public static ItemStack NuclearBomb;
    public static ItemStack IronPack;

    public static void init(){
        createFireGoldenSword();
        createLightingSword();
        createSpeedsterBoots();
        createGiantSword();
        createMarvelOfThunderStorm();
        createDragonSword();
        createExodus();
        createSelfAttackSword();
        createNuclearBomb();
        createIronPack();
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

    private static void createSpeedsterBoots(){
        ItemStack stack = new ItemStack(Material.IRON_BOOTS);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Fly Boots");
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "generic.armor", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "generic.movement_speed", 0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier1);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier2);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + "도도를 현실에서 만날 시 주어집니다.");
        lore.add(ChatColor.RED + "또는 특별한 이벤트에서 얻을 수 있습니댜.");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        SpeedSterBoots = stack;
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
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("exodus_recipe"), ExodusRecipe);
        recipe.shape("NEN", "DAD", "   ");
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
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
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("iron_pack_recipe"), IronPackRecipe);
        recipe.shape("III", "ICI", "III");
        recipe.setIngredient('C', Material.COAL);
        recipe.setIngredient('I', Material.RAW_IRON);

        Bukkit.addRecipe(recipe);
    }

    public static boolean hasLore(String lore, Player player){
        return player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lore);
    }
}