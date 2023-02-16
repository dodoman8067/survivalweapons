package kro.dodoworld.survivalweapons.commands;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SwItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("switem")){
            if(sender instanceof Player player){
                if(player.isOp()){
                    if(args.length > 0) {
                        String arg = args[0];
                        if(arg.equals("exodus")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.Exodus));
                        }
                        if(arg.equals("dragon_sword")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.DragonSword));
                        }
                        if(arg.equals("marvel_of_thunder_storm")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.MarvelOfThunderStorm));
                        }
                        if(arg.equals("giant_sword")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.GiantSword));
                        }
                        if(arg.equals("lighting_axe")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.LightingSword));
                        }
                        if(arg.equals("self_attack_sword")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.SelfAttackSword));
                        }
                        if(arg.equals("anduril")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.Anduril));
                        }
                        if(arg.equals("zombie_pigmen_sword")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.FireGoldenSword));
                        }
                        if(arg.equals("bloodlust")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.BloodLust));
                        }
                        if(arg.equals("golem_sword")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.GolemSword));
                        }
                        if(arg.equals("golden_head")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.GoldenHead));
                        }
                        if(arg.equals("excalibur")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.Excalibur));
                        }
                        if(arg.equals("magic_pickaxe")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.MagicPickaxe));
                        }
                        if(arg.equals("pandora_box")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.PandoraBox));
                        }
                        if(arg.equals("dragon_bow")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.DragonBow));
                        }
                        if(arg.equals("time_warp_pearl")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.TimeWarpPearl));
                        }
                        if(arg.equals("vampire_helmet")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.VampireHelmet));
                        }
                        if(arg.equals("poseidon_trident")){
                            player.getInventory().addItem(new ItemStack(ItemsInit.PoseidonTrident));
                        }
                    }else{
                        player.sendMessage("Usage : /switem <item_name>");
                    }
                }
            }else{
                sender.sendMessage("You Are Not Player.");
            }
        }
        return true;
    }
}
