package me.kingtux.kingserverinfo.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.kingtux.kingserverinfo.commands.Arguments;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CustomArgumentUtils {
    private static String prefix;

    public static void doArgumentWork(Arguments arguments, Player player, String prefix2) {
        prefix = prefix2;
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

    private static void sendPlayerMessage(Player p, String Message) {
        Message = Message.replace("{prefix}", prefix);
        Message = PlaceholderAPI.setPlaceholders(p, Message);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Message));
    }

    private static void sendBroudcastMessage(Player p, String Message) {
        Message = Message.replace("{prefix}", prefix);
        Message = PlaceholderAPI.setPlaceholders(p, Message);
        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Message));

    }

    private static void runConsoleCommand(Player p, String Message) {
        if (p != null) {
            Message = Message.replace("%player_displayname%", p.getName());
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), Message);
        }
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), Message);

    }

    private static void runPlayerCommand(Player p, String Message) {
        Bukkit.getServer().dispatchCommand(p, Message);
    }


}
