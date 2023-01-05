package kro.dodoworld.survivalweapons.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ExodusConfig {

    private static File file;;
    private static FileConfiguration exodusConfig;

    public static void init(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Survivalweapons").getDataFolder(), "exodus_craft_amount.yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        exodusConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getExodusConfig() {
        return exodusConfig;
    }

    public static void saveConfig(){
        try {
            exodusConfig.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void reloadConfig(){
        exodusConfig = YamlConfiguration.loadConfiguration(file);
    }
}
