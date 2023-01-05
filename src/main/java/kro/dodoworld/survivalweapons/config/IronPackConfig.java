package kro.dodoworld.survivalweapons.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class IronPackConfig {
    private static File file;
    private static FileConfiguration ironPackConfig;

    public static void init(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Survivalweapons").getDataFolder(), "iron_pack_craft_amount.yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        ironPackConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getIronPackConfig() {
        return ironPackConfig;
    }

    public static void saveConfig(){
        try {
            ironPackConfig.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void reloadConfig(){
        ironPackConfig = YamlConfiguration.loadConfiguration(file);
    }
}
