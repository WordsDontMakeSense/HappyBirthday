package me.wordsdontmakesense.happybirthday.Commands;

import me.wordsdontmakesense.happybirthday.Commands.Files.Birthdays;
import me.wordsdontmakesense.happybirthday.HappyBirthday;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BirthdayCommand implements CommandExecutor {
    HappyBirthday happyBirthday;

    public BirthdayCommand(HappyBirthday happyBirthday)
    {
        this.happyBirthday = happyBirthday;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        String first = args[0];
        String second = args[1];
        int month = Integer.parseInt(args[2]);
        int day = Integer.parseInt(args[3]);
        if(first.equalsIgnoreCase("help"))
            helpMessage(commandSender);
        else if(first.equalsIgnoreCase("get") && second != "")
            getBirthday(commandSender, second);
        else if(first.equalsIgnoreCase("set") && second != "")
            setBirthday(commandSender, second, month, day);
        else if(first.equalsIgnoreCase("remove") && second != "")
            removeBirthday(commandSender, second);
        else
            helpMessage(commandSender);
        return true;
    }

    private void helpMessage(CommandSender commandSender)
    {
        happyBirthday.sendMessage(commandSender, "");
        happyBirthday.sendMessage(commandSender, ChatColor.GOLD + "Happy" + ChatColor.LIGHT_PURPLE + "Birthday!" + ChatColor.WHITE + " Commands!");
        happyBirthday.sendMessage(commandSender, "/birthdays help" + ChatColor.GRAY + " Displays help menu");
        happyBirthday.sendMessage(commandSender, "/birthdays get (username)" + ChatColor.GRAY + " Returns player's birthday");
        happyBirthday.sendMessage(commandSender, "/birthdays set (username) (month) (day)" + ChatColor.GRAY + " Sets player's birthday");
        happyBirthday.sendMessage(commandSender, "/birthdays remove (username)" + ChatColor.GRAY + " Removes player's birthday");
        happyBirthday.sendMessage(commandSender, "");
    }

    private void getBirthday(CommandSender commandSender, String name)
    {
        if(Birthdays.contains(name))
        {
            String playersBirthday = Birthdays.getString(name);
            String[] splitBirthday = playersBirthday.split("/");
            happyBirthday.sendMessage(commandSender, name + "'s birthday is " + splitBirthday[0] + " " + splitBirthday[1], true);
        } else {
            happyBirthday.sendMessage(commandSender, name + "'s birthday is not set!", true);
        }
    }

    private void setBirthday(CommandSender commandSender, String playerName, int month, int day)
    {
        if(day <= happyBirthday.daysOfTheMonth.get(happyBirthday.months.get(month))) {
            Birthdays.set(playerName, month, day);
            happyBirthday.sendMessage(commandSender, "Set " + playerName + "'s Birthday to " + happyBirthday.months.get(month) + " " + day + "!");
        } else {
            happyBirthday.sendMessage(commandSender, happyBirthday.months.get(month) + " has less than " + day + " days in it!");
        }
    }

    private void removeBirthday(CommandSender commandSender, String playerName)
    {
        Birthdays.remove(playerName);
        happyBirthday.sendMessage(commandSender, "Removed " + playerName + "'s birthday!");
    }
}
