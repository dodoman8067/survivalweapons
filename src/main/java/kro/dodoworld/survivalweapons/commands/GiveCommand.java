package kro.dodoworld.survivalweapons.commands;

import kro.dodoworld.survivalweapons.items.ItemsInit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Deprecated : use /switem ....
 */
@Deprecated
public class GiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp() && sender instanceof Player){
            Player player = (Player) sender;
            if(command.getName().equals("givegoldensword")){
                player.getInventory().addItem(new ItemStack(ItemsInit.FireGoldenSword));
            }
            if(command.getName().equals("givelightingsword")){
                player.getInventory().addItem(new ItemStack(ItemsInit.LightingSword));
            }
            if(command.getName().equals("givegiantsword")){
                player.getInventory().addItem(new ItemStack(ItemsInit.GiantSword));
            }
            if(command.getName().equals("givemarvel")){
                player.getInventory().addItem(new ItemStack(ItemsInit.MarvelOfThunderStorm));
            }
            if(command.getName().equals("givedragonsword")){
                player.getInventory().addItem(new ItemStack(ItemsInit.DragonSword));
            }
            if(command.getName().equals("giveexodus")){
                player.getInventory().addItem(new ItemStack(ItemsInit.Exodus));
            }
        }
        return true;
    }
}
