package me.wordsdontmakesense.happybirthday.Files;

import me.wordsdontmakesense.happybirthday.HappyBirthday;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class Birthdays {
    private static File file;
    private static FileConfiguration fileConfig;
    private static HappyBirthday happyBirthday;

    public Birthdays(HappyBirthday happyBirthday)
    {
        this.happyBirthday = happyBirthday;
    }

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("HappyBirthday").getDataFolder(), "birthdays.yml");

        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch(IOException e) {
                happyBirthday.sendError("Couldn't create birthdays.yml!", true);
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
            happyBirthday.sendError("Couldn't save birthdays.yml!", true);
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

    public static void set(String key, int month, int day)
    {
        if(fileConfig.contains(key))
            fileConfig.set(key.toLowerCase(), month + "/" + day);
        else
            fileConfig.addDefault(key.toLowerCase(), month + "/" + day);
        save();
    }

    public static void remove(@NotNull CommandSender commandSender, String key)
    {
        if(fileConfig.contains(key))
            fileConfig.set(key, null);
        else
            happyBirthday.sendPrefixedMessage(commandSender, "There is no birthday stored for " + key + "!");
        save();
    }
}
