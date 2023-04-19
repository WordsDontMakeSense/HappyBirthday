package me.wordsdontmakesense.happybirthday.Commands.Files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Birthdays {
    private static File file;
    private static FileConfiguration fileConfig;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("HappyBirthday").getDataFolder(), "birthdays.yml");

        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch(IOException e) {
                System.out.println("Couldn't create birthdays.yml!");
            }
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
            System.out.println("Couldn't save birthdays.yml!");
        }
    }

    public static void reload()
    {
        fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static int countBirthdays()
    {
        return file.list().length;
    }

    public static String getString(String key)
    {
        return fileConfig.getString(key);
    }

    public static boolean contains(String key)
    {
        return fileConfig.contains(key);
    }

    public static void set(String key, int month, int day)
    {
        fileConfig.set(key, month + "/" + day);
    }

    public static void remove(String key)
    {
        fileConfig.set(key, null);
    }
}
