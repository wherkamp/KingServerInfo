package me.kingtux.kingserverinfo.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;

public class JsonManager {

    public static TextComponent MakeHoverableMessage(String Player, String HoverText) {

        if (Bukkit.getServer().getPlayer(Player) == null) {
            Player = "&4" + Player;
        } else {
            Player = "&2" + Player;
        }

        Player = ChatColor.translateAlternateColorCodes('&', Player);
        ComponentBuilder build = new ComponentBuilder(HoverText);
        TextComponent message = new TextComponent(Player);
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, build.create()));

        return message;
    }

}
