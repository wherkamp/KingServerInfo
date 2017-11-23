package me.kingtux.kingserverinfo.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.kingtux.kingserverinfo.commands.Arguments;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CustomArgumentUtils {
    public static void doArgumentWork(Arguments arguments, Player player) {
        //Player Message
        if (arguments.getPlayerMessage() != null) {
            for (String m : arguments.getPlayerMessage()) {
                sendPlayerMessage(player, m);
            }
        }
        //Broudcast Message
        if (arguments.getBroudcastMessage() != null) {
            for (String m : arguments.getBroudcastMessage()) {
                sendBroudcastMessage(player, m);
            }
        }
        //Run player Command
        if (arguments.getPlayerCommand() != null) {
            for (String c : arguments.getPlayerCommand()) {
                runPlayerCommand(player, c);
            }
        }
        //Run Console Command
        if (arguments.getConsoleCommand() != null) {
            for (String c : arguments.getConsoleCommand()) {
                runPlayerCommand(player, c);
            }
        }


    }

    public static void sendPlayerMessage(Player p, String Message) {
        Message = PlaceholderAPI.setPlaceholders(p, Message);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Message));
    }

    public static void sendBroudcastMessage(Player p, String Message) {
        Message = PlaceholderAPI.setPlaceholders(p, Message);
        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Message));

    }

    public static void runConsoleCommand(Player p, String Message) {
        if (p != null) {
            Message = Message.replace("%player_displayname%", p.getName());
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), Message);
        }
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), Message);

    }

    public static void runPlayerCommand(Player p, String Message) {
        Bukkit.getServer().dispatchCommand(p, Message);
    }


}
