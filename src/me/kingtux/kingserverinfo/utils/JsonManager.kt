package me.kingtux.kingserverinfo.utils

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit

object JsonManager {

    fun makeHoverableMessage(Player: String, HoverText: String): TextComponent {
        var Player = Player
        if (Bukkit.getServer().getPlayer(Player) != null) {
            Player = " &2" + Player
        } else {
            Player = " &4" + Player
        }
        Player = ChatColor.translateAlternateColorCodes('&', Player)
        val build = ComponentBuilder(HoverText)
        val message = TextComponent(Player)
        message.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, build.create())

        return message
    }


    fun makeLinkText(beforeLinkMessage: String?, link: String?): TextComponent {

        val build = ComponentBuilder("Click here to go to " + link!!)
        val message: String
        if (beforeLinkMessage != null && link != null) {
            message = beforeLinkMessage.replace("{link}", link)
        } else if (link == null) {
            message = "127.0.0.1"
        } else {
            message = link
        }
        val linkMessage = TextComponent(KingTuxUtils.color(message))
        linkMessage.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, build.create())
        linkMessage.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, link)
        return linkMessage
    }

}
