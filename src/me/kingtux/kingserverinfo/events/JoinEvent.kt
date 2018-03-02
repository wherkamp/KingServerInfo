package me.kingtux.kingserverinfo.events

import me.clip.placeholderapi.PlaceholderAPI
import me.kingtux.kingserverinfo.KingServerInfoMain
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinEvent(private val plugin: KingServerInfoMain) : Listener {

    @EventHandler
    fun playerJoinEvent(e: PlayerJoinEvent) {
        val api = PlaceholderAPI()
        val player = e.player
        val onJoinBroudcastMessage = plugin.configSettings!!.onJoinBroudcastMessage
        for (s in onJoinBroudcastMessage) {
            val PlaceHolderMessage = PlaceholderAPI.setPlaceholders(player, s)
            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.configSettings!!.prefix + " " + PlaceHolderMessage))
        }
        val onJoinPersonalMessage = plugin.configSettings!!.onJoinPersonalMessage
        for (s in onJoinPersonalMessage) {
            val PlaceHolderMessage = PlaceholderAPI.setPlaceholders(player, s)
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.configSettings!!.prefix + " " + PlaceHolderMessage))
        }
    }
}
