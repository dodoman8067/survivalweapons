package kro.dodoworld.survivalweapons.items.custom;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.BanList;
import org.bukkit.entity.Entity;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Date;
import java.util.Random;

public class PandoraBox implements Listener {

    private static final Random rnd = new Random();

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if(!event.getHand().equals(EquipmentSlot.HAND)) return;
            if(!ItemsInit.isPluginItem("sw_item_pandora_box", event.getPlayer())) return;
            Player player = event.getPlayer();
            if(Math.random() < 0.25){
                doGoodEffect(player);
            }else{
                doBadEffect(player);
            }
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
        }
    }

    private static void doBadEffect(Player player){
        int a = rnd.nextInt(1, 11);
        if(a == 1){
            for(int i = 0; i < 3; i++){
                Wither wither = player.getLocation().getWorld().spawn(player.getLocation(), Wither.class);
                wither.setTarget(player);
            }
            player.sendMessage(ChatColor.GOLD + "????????? ?????????????????? ??? ????????? ????????? ??????????????????!");
        }
        if(a == 2){
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 36000, 9));
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 36000, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 36000, 9));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 36000, 4));
            player.sendMessage(ChatColor.GOLD + "????????? ??????????????? ????????? ???????????????!");
        }
        if(a == 3){
            for(ItemStack item : player.getInventory()){
                if(item != null){
                    item.setAmount(0);
                }
            }
            player.sendMessage(ChatColor.GOLD + "????????? ??????????????? ????????? ?????? ???????????? ????????? ????????????!");
        }
        if(a == 4){
            player.getWorld().createExplosion(player.getLocation(), 12f, true, true);
            player.sendMessage(ChatColor.GOLD + "????????? ??????????????? ????????? ????????? ??????????????????!");
        }
        if(a == 5){
            for(Player player1 : Bukkit.getOnlinePlayers()){
                if(player1.getGameMode().equals(GameMode.SURVIVAL) || player1.getGameMode().equals(GameMode.ADVENTURE)){
                    player1.setHealth(0);
                }
            }
            player.sendMessage(ChatColor.GOLD + "????????? ??????????????? ?????? ???????????? ???????????????!");
        }
        if(a == 6){
            for(int i = 0; i<25; i++){
                TNTPrimed tnt = player.getLocation().getWorld().spawn(player.getLocation(), TNTPrimed.class);
                tnt.setSource(player);
                tnt.setFuseTicks(100);
                tnt.setVelocity(new Vector(Math.random(), 2, Math.random()));
            }
            player.sendMessage(ChatColor.GOLD + "?????? ????????? 25?????? TNT??? ?????????????????????!");
        }
        if(a == 7){
            IronGolem golem = player.getLocation().getWorld().spawn(player.getLocation(), IronGolem.class);
            golem.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
            golem.setPlayerCreated(false);
            golem.setTarget(player);
            player.sendMessage(ChatColor.GOLD + "????????? ?????????????????? ????????? ??????????????????!");
        }
        if(a == 8){
            Bukkit.getServer().getPlayer(player.getUniqueId()).kickPlayer(ChatColor.GOLD + "???????????? ????????? ??????????????? ???????????? ?????????????????????.");
            Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), ChatColor.GOLD + "???????????? ????????? ???????????????.", new Date(System.currentTimeMillis()+60*60*1000), null);
        }
        if(a == 9){
            for(Entity entity : player.getNearbyEntities(45, 45, 45)){
                if(entity instanceof LivingEntity){
                    ((LivingEntity) entity).setHealth(0);
                }
            }
            player.sendMessage(ChatColor.GOLD + "????????? ????????? ????????? ?????? ??????????????? ?????? ???????????????!");
        }
        if(a == 10){
            for(int i = 0; i< 15; i++){
                Zombie zombie = player.getLocation().getWorld().spawn(player.getLocation().add((Math.random() * 10), 0, (Math.random() * 10)), Zombie.class);
                zombie.setTarget(player);
                zombie.setPersistent(true);
                zombie.setBaby();
                zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                zombie.getEquipment().setHelmetDropChance(0f);
                zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                zombie.getEquipment().setChestplateDropChance(0f);
                zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                zombie.getEquipment().setLeggingsDropChance(0f);
                zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                zombie.getEquipment().setBootsDropChance(0f);
                zombie.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
                zombie.getEquipment().setItemInMainHandDropChance(0f);
            }
            player.sendMessage(ChatColor.GOLD + "????????? ???????????? ?????? ???????????? ??????????????????!");
        }
    }

    private static void doGoodEffect(Player player){
        int a = rnd.nextInt(1, 11);
        if(a == 1){
            if(player.getInventory().firstEmpty() == -1){
                player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.GOLDEN_APPLE, 2));
            }else{
                player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
            }
            player.sendMessage(ChatColor.GOLD + "???????????? ?????? ?????? 2?????? ?????????????????????!");
        }
        if(a == 2){
            ItemStack stack = new ItemStack(Material.ENCHANTED_BOOK);
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) stack.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "????????? ???");
            meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 4, false);
            meta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
            meta.addStoredEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, false);
            stack.setItemMeta(meta);
            if(player.getInventory().firstEmpty() == -1){
                player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(stack));
            }else{
                player.getInventory().addItem(new ItemStack(stack));
            }
            player.sendMessage(ChatColor.GOLD + "???????????? ????????? ?????? ?????????????????????!");
        }
        if(a == 3){
            player.giveExpLevels(50);
            player.sendMessage(ChatColor.GOLD + "???????????? 50????????? ???????????? ?????????????????????!");
        }
        if(a == 4){
            if(player.getInventory().firstEmpty() == -1){
                player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(ItemsInit.MagicPickaxe));
            }else{
                player.getInventory().addItem(new ItemStack(ItemsInit.MagicPickaxe));
            }
            player.sendMessage(ChatColor.GOLD + "???????????? ????????? ???????????? ?????????????????????!");
        }
        if(a == 5){
            if(player.getInventory().firstEmpty() == -1){
                player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.ENDER_EYE));
            }else{
                player.getInventory().addItem(new ItemStack(Material.ENDER_EYE));
            }
            player.sendMessage(ChatColor.GOLD + "???????????? ????????? ?????? ?????????????????????!");
        }
        if(a == 6){
            if(player.getInventory().firstEmpty() == -1){
                for(int i = 0; i < 4; i++){
                    player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(ItemsInit.TimeWarpPearl));
                }
            }else{
                for(int i = 0; i<4; i++){
                    player.getInventory().addItem(new ItemStack(ItemsInit.TimeWarpPearl));
                }
            }

            player.sendMessage(ChatColor.GOLD + "???????????? ????????? ?????? ????????? ?????????????????????!");
        }
        if(a == 7){
            if(player.getInventory().firstEmpty() == -1){
                player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.GOLD_INGOT, 10));
            }else{
                player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 10));
            }
            player.sendMessage(ChatColor.GOLD + "???????????? ????????? 10?????? ?????????????????????!");
        }
        if(a == 8){
            if(player.getInventory().firstEmpty() == -1){
                player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.IRON_INGOT, 10));
            }else{
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 10));
            }
            player.sendMessage(ChatColor.GOLD + "???????????? ?????? 10?????? ?????????????????????!");
        }
        if(a == 9){
            if(player.getInventory().firstEmpty() == -1){
                player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(ItemsInit.GoldenHead));
            }else{
                player.getInventory().addItem(new ItemStack(ItemsInit.GoldenHead));
            }
            player.sendMessage(ChatColor.GOLD + "???????????? ?????? ????????? ?????????????????????!");
        }
        if(a == 10){
            double q = Math.random() * 100;
            if(q <= 1){
                if(player.getInventory().firstEmpty() == -1){
                    player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(ItemsInit.Anduril));
                }else{
                    player.getInventory().addItem(new ItemStack(ItemsInit.Anduril));
                }
                player.sendMessage(ChatColor.GOLD + "???????????? ???????????? ?????????????????????!");
                player.sendMessage(ChatColor.GOLD + "?????? ??????..?");
            }else{
                if(player.getInventory().firstEmpty() == -1){
                    player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.BLAZE_ROD));
                }else{
                    player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD));
                }
                player.sendMessage(ChatColor.GOLD + "???????????? ???????????? ????????? ?????????????????????!");
            }
        }
    }
}
