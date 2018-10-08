package me.kingtux.kingserverinfo.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;

public class JsonManager {

    public static TextComponent makeHoverableMessage(String Player, String HoverText) {
        if (Bukkit.getServer().getPlayer(Player) != null) {
            Player = " &2" + Player;
        } else {
            Player = " &4" + Player;
        }
        Player = ChatColor.translateAlternateColorCodes('&', Player);
        ComponentBuilder build = new ComponentBuilder(HoverText);
        TextComponent message = new TextComponent(Player);
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, build.create()));

        return message;
    }


    public static TextComponent makeLinkText(String beforeLinkMessage, String link) {

        ComponentBuilder build = new ComponentBuilder("Click here to go to " + link);
        String message;
        if (beforeLinkMessage != null && link != null) {
            message = beforeLinkMessage.replace("{link}", link);
        } else if (link == null) {
            message = "127.0.0.1";
        } else {
            message = link;
        }
        TextComponent linkMessage = new TextComponent(TuxUtils.color(message));
        linkMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, build.create()));
        linkMessage.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link));
        return linkMessage;
    }

}
