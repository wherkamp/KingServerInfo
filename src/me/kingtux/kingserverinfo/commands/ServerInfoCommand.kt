package me.kingtux.kingserverinfo.commands

import me.clip.placeholderapi.PlaceholderAPI
import me.kingtux.kingserverinfo.KingServerInfoMain
import me.kingtux.kingserverinfo.utils.CustomArgumentUtils
import me.kingtux.kingserverinfo.utils.JsonManager
import org.bukkit.ChatColor
import org.bukkit.ChatColor.translateAlternateColorCodes
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.command.defaults.BukkitCommand
import org.bukkit.entity.Player
import java.util.*

class ServerInfoCommand(name: String, description: String, private val plugin: KingServerInfoMain) : BukkitCommand(name) {
    private val args: MutableList<String>
    private val BasePerm = "kingserverinfo.command"

    init {
        this.description = description
        this.usageMessage = "/<command> <task>"
        this.permission = "kingserverinfo.command"
        this.aliases = ArrayList()

        args = ArrayList()
        for (arguments in plugin.configSettings?.customArguments!!) {
            args.add(arguments.argument)
        }
        args.add("reload")
        args.add("owner")
        args.add("staff")
        args.add("rules")
        args.add("media")
        args.add("help")

    }

    @Throws(IllegalArgumentException::class)
    override fun tabComplete(sender: CommandSender, alias: String, args: Array<String>, location: Location?): List<String> {
        val a = ArrayList<String>()
        if (sender is Player) {
            for (arg in args) {
                if (sender.hasPermission("kingserverinfo.command." + arg)) {
                    a.add(arg)
                }

            }
        }
        return a
    }

    @Throws(IllegalArgumentException::class)
    override fun tabComplete(sender: CommandSender, alias: String, args: Array<String>): List<String> {
        val a = ArrayList<String>()
        if (sender is Player) {
            for (arg in args) {
                if (sender.hasPermission("kingserverinfo.command." + arg.toLowerCase())) {
                    a.add(arg)
                }

            }
        }
        return a
    }

    override fun execute(sender: CommandSender, s: String, args: Array<String>): Boolean {
        if (sender is Player) {

            if (args.size == 0) {
                if (sender.hasPermission(BasePerm + ".serverinfo")) {
                    for (Message in plugin.configSettings?.baseCommandDataInfo!!) {
                        var newMessage = Message.replace("{prefix}", plugin.configSettings?.prefix!!, true)
                        newMessage = translateAlternateColorCodes('&', newMessage)
                        newMessage = PlaceholderAPI.setPlaceholders(sender, newMessage)

                        sender.sendMessage(newMessage)
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_RED.toString() + "You lack the permissions to do that")
                }

            } else {
                if (args[0].equals("reload", ignoreCase = true)) {
                    if (sender.hasPermission(BasePerm + ".reload")) {
                        plugin.reloadPlugin()
                        sender.sendMessage(translateAlternateColorCodes('&', plugin.configSettings?.prefix!! + " Config Reloaded"))
                    } else {
                        sender.sendMessage(translateAlternateColorCodes('&', "You do not have permission to run this command"))
                    }
                } else if (args[0].equals("media", ignoreCase = true)) {
                    if (sender.hasPermission(BasePerm + ".media")) {
                        sender.openInventory(plugin.mediaGui?.createMediaGui())
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED.toString() + "You lack the permissions to do that")

                    }
                } else if (args[0].equals("rules", ignoreCase = true)) {
                    if (sender.hasPermission(BasePerm + ".rules")) {
                        for (rulesLine in plugin.configSettings?.rules!!) {
                            var newRulesLine = rulesLine.replace("{prefix}", plugin.configSettings?.prefix!!, true)
                            newRulesLine = PlaceholderAPI.setPlaceholders(sender, newRulesLine)

                            sender.sendMessage(translateAlternateColorCodes('&', newRulesLine))
                        }
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED.toString() + "You lack the permissions to do that")
                    }
                } else if (args[0].equals("owner", ignoreCase = true)) {
                    if (sender.hasPermission(BasePerm + ".owner")) {
                        sender.sendMessage(translateAlternateColorCodes('&', "&2Our Owner is "))
                        sender.spigot().sendMessage(JsonManager.makeHoverableMessage(
                                plugin.configSettings?.owner!!,
                                plugin.configSettings?.ownerInfo!!))
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED.toString() + "You lack the permissions to do that")
                    }
                } else if (args[0].equals("staff", ignoreCase = true)) {
                    if (sender.hasPermission(BasePerm + ".staff")) {
                        sender.sendMessage(translateAlternateColorCodes('&', "&2If the name is green the player is online!"))
                        for (textComponent in plugin.configSettings?.staffList!!.keys) {

                            sender.spigot().sendMessage(
                                    JsonManager.makeHoverableMessage(textComponent, plugin.configSettings?.staffList!!.get(textComponent)!!))

                        }
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED.toString() + "You lack the permissions to do that")
                    }
                } else if (args[0].equals("help", ignoreCase = true)) {
                    if (sender.hasPermission(BasePerm + ".help")) {
                        var argumentHelp = "&2The Base Command is equal to /" + plugin.configSettings?.serverInfoCommand!! + ".\n" +
                                "&2 All the subs commands will be listed below." +
                                "\n &2 staff: &Awhich list all the staff" +
                                "\n &2 owner: &Awhich will list the owner" +
                                "\n &2 media: &Awhich will open the media gui" +
                                "\n &2 rules: &Awhich will list the rules" +
                                "\n &2 Just the Basecommand will list the general server info,\n"

                        for (arguments in plugin.configSettings?.customArguments!!) {
                            argumentHelp = argumentHelp + arguments.argument + ": &A" + arguments.description + ", \n &2"
                        }
                        sender.sendMessage(translateAlternateColorCodes('&', argumentHelp))

                    } else {
                        sender.sendMessage(ChatColor.DARK_RED.toString() + "You lack the permissions to do that")
                    }
                } else {
                    var InvalidArgument: Boolean? = true

                    for (arguments in plugin.configSettings?.customArguments!!) {
                        if (sender.hasPermission(BasePerm + "." + arguments.argument)) {
                            if (args[0].equals(arguments.argument, ignoreCase = true)) {
                                InvalidArgument = false
                                CustomArgumentUtils.doArgumentWork(arguments, sender, plugin.configSettings?.prefix!!)
                                break
                            } else if (arguments.alias != null) {
                                if (InvalidArgument!!) {
                                    for (argumentAlias in arguments.alias) {
                                        if (args[0].equals(argumentAlias, ignoreCase = true)) {
                                            InvalidArgument = false

                                            CustomArgumentUtils.doArgumentWork(arguments, sender, plugin.configSettings?.prefix!!)
                                            break
                                        }
                                    }
                                }
                            }
                        } else {
                            sender.sendMessage(ChatColor.DARK_RED.toString() + "You lack the permissions to do that")
                        }
                    }
                    if (InvalidArgument!!) {
                        sender.sendMessage(translateAlternateColorCodes('&', "&4Invalid argument"))

                    }
                }

            }

        } else {
            if (args[0].equals("reload", ignoreCase = true)) {
                plugin.reloadPlugin()
                sender.sendMessage(translateAlternateColorCodes('&', "Updated Config"))
            } else {
                sender.sendMessage("You must be a player to run this command")
            }
        }
        return false
    }
}

