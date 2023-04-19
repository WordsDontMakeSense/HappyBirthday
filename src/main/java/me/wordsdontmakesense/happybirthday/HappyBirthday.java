package me.wordsdontmakesense.happybirthday;

import me.wordsdontmakesense.happybirthday.Commands.BirthdayCommand;
import me.wordsdontmakesense.happybirthday.Files.Birthdays;
import me.wordsdontmakesense.happybirthday.Files.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public final class HappyBirthday extends JavaPlugin {
    public static String prefix;
    public static HashMap<Integer, String> months = new HashMap<>();
    public static HashMap<String, Integer> daysOfTheMonth = new HashMap<>();

    @Override
    public void onEnable() {
        setMonthInfo();
        getCommand("birthday").setExecutor(new BirthdayCommand(this));
        logMessage(ChatColor.GOLD + "Loading Birthdays!");

        Birthdays.setup();
        Birthdays.get().options().copyDefaults(true);
        Birthdays.save();

        Config.setup();
        Config.get().options().copyDefaults(true);
        Config.save();

        prefix = Config.get().getString("prefix");

        int birthdays = Birthdays.countBirthdays();
        logMessage(ChatColor.GOLD + "Found " + ChatColor.DARK_PURPLE + birthdays + ChatColor.GOLD + " Birthdays!");

        int interval = Config.get().getInt("birthdayChatAnnouncementInterval");
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {

        }, interval, interval);
    }

    @Override
    public void onDisable() {
        logMessage(ChatColor.GOLD + "Goodbye! :)");
        Birthdays.save();
    }


    public void logMessage(String message)
    {
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }

    public void sendError(String message, boolean usePrefix)
    {
        String prefix = ChatColor.YELLOW + "[" + ChatColor.GOLD + "Happy" + ChatColor.LIGHT_PURPLE + "Birthday" + ChatColor.YELLOW + "] ";
        if(!usePrefix)
        {
            prefix = "";
        }
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.RED + message);
    }

    public void sendPrefixedMessage(@NotNull CommandSender commandSender, String message)
    {
        commandSender.sendMessage(prefix + message);
    }

    public void sendMessage(@NotNull CommandSender commandSender, String message)
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
