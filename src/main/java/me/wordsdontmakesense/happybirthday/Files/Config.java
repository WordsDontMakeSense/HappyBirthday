package me.wordsdontmakesense.happybirthday.Files;

import me.wordsdontmakesense.happybirthday.HappyBirthday;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    private static File file;
    private static FileConfiguration fileConfig;
    private static HappyBirthday happyBirthday;

    public Config(HappyBirthday happyBirthday)
    {
        this.happyBirthday = happyBirthday;
    }

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("HappyBirthday").getDataFolder(), "config.yml");

        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch(IOException e) {
                happyBirthday.sendError("Couldn't create config.yml!", true);
            }
        } else {
            reload();
        }
        fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get()
    {
        return fileConfig;
    }

    public static void save()
    {
        try {
            fileConfig.save(file);
        } catch (IOException e)
        {
            happyBirthday.sendError("Couldn't save config.yml!", true);
        }
    }

    public static void reload()
    {
        fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    private void setDefaults()
    {
        fileConfig.addDefault("prefix", "§e[§6Happy§dBirthday§e] §6");
        fileConfig.addDefault("permission", "birthday");
        fileConfig.addDefault("announceBirthdayOnJoin", true);
        fileConfig.addDefault("announceBirthdaysInChat", true);
        fileConfig.addDefault("birthdayChatAnnouncementInterval", 6000);
    }
}
