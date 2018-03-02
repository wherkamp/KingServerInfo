package me.kingtux.kingserverinfo.events

import me.kingtux.kingserverinfo.KingServerInfoMain
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class LeaveEvent(private val plugin: KingServerInfoMain) : Listener {

    @EventHandler
    fun playerLeaveEvent(e: PlayerQuitEvent) {
        val player = e.player
        var leaveMessage = plugin.configSettings!!.onLeaveMessage
        for (s in leaveMessage) {
            var s = s.replace("{player}", player.name)
            Bukkit.getServer().broadcastMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            plugin.configSettings!!.prefix + " " + s))
        }
    }
}
