package kro.dodoworld.survivalweapons.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BloodLustConfig {
    private static File file;
    private static FileConfiguration bloodLustConfig;

    public static void init(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Survivalweapons").getDataFolder(), "bloodlust_craft_amount.yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        bloodLustConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getBloodLustConfig() {
        return bloodLustConfig;
    }

    public static void saveConfig(){
        try {
            bloodLustConfig.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void reloadConfig(){
        bloodLustConfig = YamlConfiguration.loadConfiguration(file);
    }
}
