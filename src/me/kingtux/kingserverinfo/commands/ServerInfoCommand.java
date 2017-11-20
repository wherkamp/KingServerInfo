package me.kingtux.kingserverinfo.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.kingtux.kingserverinfo.KingServerInfoMain;
import me.kingtux.kingserverinfo.utils.JsonManager;
import net.md_5.bungee.api.chat.TextComponent;
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
                        Message = PlaceholderAPI.setPlaceholders(player, Message);
                        player.sendMessage(Message);
                    }
                } else {
                    player.sendMessage(translateAlternateColorCodes('&', "&4You do not have permission for this command."));
                }

            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (player.hasPermission("kingserverinfo.command.reload")) {
                        plugin.getConfigSettings().getAllSettings();
                        player.sendMessage(translateAlternateColorCodes('&', plugin.getConfigSettings().getPrefix() + " Config Reloaded"));
                    } else {
                        player.sendMessage(translateAlternateColorCodes('&', "You do not have permission to run this command"));
                    }
                } else if (args[0].equalsIgnoreCase("media")) {
                    if (player.hasPermission(BasePerm + ".media")) {
                        player.openInventory(plugin.getMediaGui().createMediaGui());
                    } else {
                        player.sendMessage(translateAlternateColorCodes('&', "&4You do not have permission for this command."));

                    }
                } else if (args[0].equalsIgnoreCase("rules")) {
                    if (player.hasPermission(BasePerm + ".rules")) {
                        for (String rulesLine : plugin.getConfigSettings().getRules()) {
                            rulesLine = PlaceholderAPI.setPlaceholders(player, rulesLine);

                            player.sendMessage(translateAlternateColorCodes('&', rulesLine));
                        }
                    } else {
                        player.sendMessage(translateAlternateColorCodes('&', "&4You do not have permission for this command."));
                    }
                } else if (args[0].equalsIgnoreCase("owner")) {
                    player.sendMessage(translateAlternateColorCodes('&', "&2Our Owner is "));
                    player.spigot().sendMessage(JsonManager.MakeHoverableMessage(
                            plugin.getConfigSettings().getOwner(),
                            plugin.getConfigSettings().getOwnerInfo()));
                } else if (args[0].equalsIgnoreCase("staff")) {
                    player.sendMessage(translateAlternateColorCodes('&', "&2If the name is green the player is online!"));
                    for (TextComponent textComponent : plugin.getConfigSettings().getStaffList()) {

                        player.spigot().sendMessage(textComponent);

                    }
                } else if (args[0].equalsIgnoreCase("help")) {

                    String argumentHelp = "&2The Base Command is equal to /" + plugin.getConfigSettings().getServerInfoCommand() + ".\n" +
                            "&2 All the subs commands will be listed below." +
                            "\n &2 staff which list all the staff" +
                            "\n &2 owner which will list the owner" +
                            "\n &2 media which will open the media gui" +
                            "\n &2 rules which will list the rules" +
                            "\n &2 Just the Basecommand will list the general server info, \n";
                    for (Arguments arguments : plugin.getConfigSettings().getCustomArguments()) {
                        argumentHelp = argumentHelp + " " + arguments.getArgument() + arguments.getDescription() + ", \n &2";
                    }
                    player.sendMessage(translateAlternateColorCodes('&', argumentHelp));


                } else {
                    Boolean InvalidArgument = true;

                    for (Arguments arguments : plugin.getConfigSettings().getCustomArguments()) {
                        if (args[0].equalsIgnoreCase(arguments.getArgument())) {
                            for (String m : arguments.getMessage()) {
                                InvalidArgument = false;
                                m = PlaceholderAPI.setPlaceholders(player, m);
                                player.sendMessage(translateAlternateColorCodes('&', m));
                            }
                            break;
                        } else if (arguments.getAlias() != null) {
                            if (InvalidArgument == true) {
                                for (String argumentAlias : arguments.getAlias()) {
                                    if (args[0].equalsIgnoreCase(argumentAlias)) {
                                        for (String m : arguments.getMessage()) {
                                            InvalidArgument = false;
                                            m = PlaceholderAPI.setPlaceholders(player, m);
                                            player.sendMessage(translateAlternateColorCodes('&', m));
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (InvalidArgument == true) {
                        player.sendMessage(translateAlternateColorCodes('&', "&4Invalid argument"));

                    } else {
                    }
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
        return false;
    }
}
