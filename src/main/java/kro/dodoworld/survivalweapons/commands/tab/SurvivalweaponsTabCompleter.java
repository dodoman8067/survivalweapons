package kro.dodoworld.survivalweapons.commands.tab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SurvivalweaponsTabCompleter implements TabCompleter {

    private final List<String> commandArgs = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp()){
            if(commandArgs.isEmpty()){
                commandArgs.add("exodus");
                commandArgs.add("marvel_of_thunder_storm");
                commandArgs.add("dragon_sword");
                commandArgs.add("giant_sword");
                commandArgs.add("self_attack_sword");
                commandArgs.add("lighting_axe");
                commandArgs.add("anduril");
                commandArgs.add("bloodlust");
            }

            return commandArgs;
        }
        return null;
    }
}
