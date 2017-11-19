package me.kingtux.kingserverinfo.commands;

import me.kingtux.kingserverinfo.KingServerInfoMain;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ServerInfoCommand extends BukkitCommand {
    private KingServerInfoMain plugin;

    public ServerInfoCommand(String name, String description, KingServerInfoMain pl) {
        super(name);
        this.description = description;
        this.usageMessage = "/<command> <task>";
        this.setPermission("kingserverinfo.kingserverinfocommand");
        this.setAliases(new ArrayList<String>());

        plugin = pl;


    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("reload")) {
                if (player.hasPermission("kingserverinfo.kingserverinfocommand.reload")) {
                    plugin.getConfigSettings().getAllSettings();
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "You do not have permission to fun this command"));
                }
            }
        } else {
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.getConfigSettings().getAllSettings();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "Updated Config"));


            } else {
                sender.sendMessage("You must be a player to run this command");
            }
        }
        return false;
    }
}
