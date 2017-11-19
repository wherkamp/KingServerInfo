package me.kingtux.kingserverinfo.commands;

import me.kingtux.kingserverinfo.KingServerInfoMain;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public class ServerInfoCommand extends BukkitCommand {
    private KingServerInfoMain plugin;
    private String BasePerm = "kingserverinfo.command";

    public ServerInfoCommand(String name, String description, KingServerInfoMain pl) {
        super(name);
        this.description = description;
        this.usageMessage = "/<command> <task>";
        this.setPermission("kingserverinfo.command");
        this.setAliases(new ArrayList<String>());

        plugin = pl;


    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                if (player.hasPermission(BasePerm + "serverinfo")) {
                    for (String Message : plugin.getConfigSettings().getBaseCommandDataInfo()) {
                        Message = translateAlternateColorCodes('&', Message);
                        Message = Message.replace("{player}", player.getName());
                        player.sendMessage(Message);
                    }
                } else {
                    player.sendMessage(translateAlternateColorCodes('&', "&4You do not have permission forthis command."));
                }

            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (player.hasPermission("kingserverinfo.command.reload")) {
                        plugin.getConfigSettings().getAllSettings();
                    } else {
                        player.sendMessage(translateAlternateColorCodes('&', "You do not have permission to fun this command"));
                    }
                } else if (args[0].equalsIgnoreCase("media")) {
                    player.openInventory(plugin.getMediaGui().createMediaGui());
                }

            }

        } else {
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.getConfigSettings().getAllSettings();
                sender.sendMessage(translateAlternateColorCodes('&', "Updated Config"));


            } else {
                sender.sendMessage("You must be a player to run this command");
            }
        }
        System.out.println("Command Finished");
        return false;
    }
}
