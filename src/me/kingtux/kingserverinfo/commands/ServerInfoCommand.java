package me.kingtux.kingserverinfo.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.kingtux.kingserverinfo.KingServerInfoMain;
import me.kingtux.kingserverinfo.utils.CustomArgumentUtils;
import me.kingtux.kingserverinfo.utils.JsonManager;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
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
                if (player.hasPermission(BasePerm + ".serverinfo")) {
                    for (String Message : plugin.getConfigSettings().getBaseCommandDataInfo()) {
                        Message = Message.replace("{prefix}", plugin.getConfigSettings().getPrefix());
                        Message = translateAlternateColorCodes('&', Message);
                        Message = PlaceholderAPI.setPlaceholders(player, Message);

                        player.sendMessage(Message);
                    }
                } else {
                    player.sendMessage(ChatColor.DARK_RED + "You lack the permissions to do that");
                }

            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (player.hasPermission(BasePerm + ".reload")) {
                        plugin.reloadPlugin();
                        player.sendMessage(translateAlternateColorCodes('&', plugin.getConfigSettings().getPrefix() + " Config Reloaded"));
                    } else {
                        player.sendMessage(translateAlternateColorCodes('&', "You do not have permission to run this command"));
                    }
                } else if (args[0].equalsIgnoreCase("media")) {
                    if (player.hasPermission(BasePerm + ".media")) {
                        player.openInventory(plugin.getMediaGui().createMediaGui());
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "You lack the permissions to do that");

                    }
                } else if (args[0].equalsIgnoreCase("rules")) {
                    if (player.hasPermission(BasePerm + ".rules")) {
                        for (String rulesLine : plugin.getConfigSettings().getRules()) {
                            rulesLine = rulesLine.replace("{prefix}", plugin.getConfigSettings().getPrefix());
                            rulesLine = PlaceholderAPI.setPlaceholders(player, rulesLine);

                            player.sendMessage(translateAlternateColorCodes('&', rulesLine));
                        }
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "You lack the permissions to do that");
                    }
                } else if (args[0].equalsIgnoreCase("owner")) {
                    if (player.hasPermission(BasePerm + ".owner")) {
                        player.sendMessage(translateAlternateColorCodes('&', "&2Our Owner is "));
                        player.spigot().sendMessage(JsonManager.MakeHoverableMessage(
                                plugin.getConfigSettings().getOwner(),
                                plugin.getConfigSettings().getOwnerInfo()));
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "You lack the permissions to do that");
                    }
                } else if (args[0].equalsIgnoreCase("staff")) {
                    if (player.hasPermission(BasePerm + ".staff")) {
                        player.sendMessage(translateAlternateColorCodes('&', "&2If the name is green the player is online!"));
                        for (TextComponent textComponent : plugin.getConfigSettings().getStaffList()) {

                            player.spigot().sendMessage(textComponent);

                        }
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "You lack the permissions to do that");
                    }
                } else if (args[0].equalsIgnoreCase("help")) {
                    if (player.hasPermission(BasePerm + ".help")) {
                        String argumentHelp = "&2The Base Command is equal to /" + plugin.getConfigSettings().getServerInfoCommand() + ".\n" +
                                "&2 All the subs commands will be listed below." +
                                "\n &2 staff: &Awhich list all the staff" +
                                "\n &2 owner: &Awhich will list the owner" +
                                "\n &2 media: &Awhich will open the media gui" +
                                "\n &2 rules: &Awhich will list the rules" +
                                "\n &2 Just the Basecommand will list the general server info,\n";

                        for (Arguments arguments : plugin.getConfigSettings().getCustomArguments()) {
                            argumentHelp = argumentHelp + arguments.getArgument() + ": &A" + arguments.getDescription() + ", \n &2";
                        }
                        player.sendMessage(translateAlternateColorCodes('&', argumentHelp));

                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "You lack the permissions to do that");
                    }
                } else {
                    Boolean InvalidArgument = true;

                    for (Arguments arguments : plugin.getConfigSettings().getCustomArguments()) {
                        if (player.hasPermission(BasePerm + "." + arguments.getArgument())) {
                            if (args[0].equalsIgnoreCase(arguments.getArgument())) {
                                InvalidArgument = false;
                                CustomArgumentUtils.doArgumentWork(arguments, player, plugin.getConfigSettings().getPrefix());
                                break;
                            } else if (arguments.getAlias() != null) {
                                if (InvalidArgument) {
                                    for (String argumentAlias : arguments.getAlias()) {
                                        if (args[0].equalsIgnoreCase(argumentAlias)) {
                                            InvalidArgument = false;

                                            CustomArgumentUtils.doArgumentWork(arguments, player, plugin.getConfigSettings().getPrefix());
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            player.sendMessage(ChatColor.DARK_RED + "You lack the permissions to do that");
                        }
                    }
                    if (InvalidArgument) {
                        player.sendMessage(translateAlternateColorCodes('&', "&4Invalid argument"));

                    }
                }

            }

        } else {
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadPlugin();
                sender.sendMessage(translateAlternateColorCodes('&', "Updated Config"));
            } else {
                sender.sendMessage("You must be a player to run this command");
            }
        }
        return false;
    }
}
