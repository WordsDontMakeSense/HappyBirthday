package me.wordsdontmakesense.happybirthday;

import me.wordsdontmakesense.happybirthday.Commands.BirthdayCommand;
import me.wordsdontmakesense.happybirthday.Commands.Files.Birthdays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public final class HappyBirthday extends JavaPlugin {
    public static HashMap<Integer, String> months = new HashMap<>();
    public static HashMap<String, Integer> daysOfTheMonth = new HashMap<>();

    @Override
    public void onEnable() {
        setMonthInfo();
        getCommand("birthday").setExecutor(new BirthdayCommand(this));
        sendMessage(ChatColor.GOLD + "Loading Birthdays!", true);

        Birthdays.setup();
        Birthdays.get().options().copyDefaults(true);
        Birthdays.save();

        int birthdays = Birthdays.countBirthdays();
        sendMessage(ChatColor.GOLD + "Found " + ChatColor.DARK_PURPLE + birthdays + ChatColor.GOLD + " Birthdays!", true);
    }

    @Override
    public void onDisable() {
        sendMessage(ChatColor.GOLD + "Goodbye! :)");
    }

    public void sendMessage(String message, boolean usePrefix)
    {
        String prefix = ChatColor.YELLOW + "[" + ChatColor.GOLD + "Happy" + ChatColor.LIGHT_PURPLE + "Birthday" + ChatColor.YELLOW + "] ";
        if(!usePrefix)
        {
            prefix = "";
        }
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + message);
    }

    public void sendMessage(String message)
    {
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }

    public void sendMessage(CommandSender commandSender, String message, boolean usePrefix)
    {
        String prefix = ChatColor.YELLOW + "[" + ChatColor.GOLD + "Happy" + ChatColor.LIGHT_PURPLE + "Birthday" + ChatColor.YELLOW + "] ";
        if(!usePrefix)
        {
            prefix = "";
        }
        commandSender.sendMessage(prefix + message);
    }

    public void sendMessage(CommandSender commandSender, String message)
    {
        commandSender.sendMessage(message);
    }

    private void setMonthInfo()
    {
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");
        daysOfTheMonth.put("January", 31);
        daysOfTheMonth.put("February", 29);
        daysOfTheMonth.put("March", 31);
        daysOfTheMonth.put("April", 30);
        daysOfTheMonth.put("May", 31);
        daysOfTheMonth.put("June", 30);
        daysOfTheMonth.put("July", 31);
        daysOfTheMonth.put("August", 31);
        daysOfTheMonth.put("September", 30);
        daysOfTheMonth.put("October", 31);
        daysOfTheMonth.put("November", 30);
        daysOfTheMonth.put("December", 31);
    }
}
