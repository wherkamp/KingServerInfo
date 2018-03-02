package me.kingtux.kingserverinfo.utils

import me.clip.placeholderapi.PlaceholderAPI
import me.kingtux.kingserverinfo.commands.Arguments
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object CustomArgumentUtils {
    private var prefix: String? = null

    fun doArgumentWork(arguments: Arguments, player: Player, prefix2: String) {
        prefix = prefix2
        //Player Message
        if (arguments.playerMessage != null) {
            for (m in arguments.playerMessage) {

                sendPlayerMessage(player, m)
            }
        }
        //Broudcast Message
        if (arguments.broudcastMessage != null) {
            for (m in arguments.broudcastMessage) {
                sendBroudcastMessage(player, m)
            }
        }
        //Run player Command
        if (arguments.playerCommand != null) {
            for (c in arguments.playerCommand) {
                runPlayerCommand(player, c)
            }
        }
        //Run Console Command
        if (arguments.consoleCommand != null) {
            for (c in arguments.consoleCommand) {
                runPlayerCommand(player, c)
            }
        }


    }

    private fun sendPlayerMessage(p: Player, Message: String) {
        var Message = Message
        Message = Message.replace("{prefix}", prefix!!)
        Message = PlaceholderAPI.setPlaceholders(p, Message)
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Message))
    }

    private fun sendBroudcastMessage(p: Player, Message: String) {
        var Message = Message
        Message = Message.replace("{prefix}", prefix!!)
        Message = PlaceholderAPI.setPlaceholders(p, Message)
        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Message))

    }

    private fun runConsoleCommand(p: Player?, Message: String) {
        var Message = Message
        if (p != null) {
            Message = Message.replace("%player_displayname%", p.name)
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().consoleSender, Message)
        }
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().consoleSender, Message)

    }

    private fun runPlayerCommand(p: Player, Message: String) {
        Bukkit.getServer().dispatchCommand(p, Message)
    }


}
