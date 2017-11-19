package me.kingtux.kingserverinfo.events;

import me.kingtux.kingserverinfo.KingServerInfoMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinEvent implements Listener {
    private KingServerInfoMain plugin;

    public JoinEvent(KingServerInfoMain pl) {
        plugin = pl;
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        List<String> onJoinBroudcastMessage = plugin.getConfigSettings().getOnJoinBroudcastMessage();
        for (String s : onJoinBroudcastMessage) {
            s = s.replace("{player}", player.getName());
            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfigSettings().getPrefix() + " " + s));
        }
        List<String> onJoinPersonalMessage = plugin.getConfigSettings().getOnJoinPersonalMessage();
        for (String s : onJoinPersonalMessage) {
            s = s.replace("{player}", player.getName());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfigSettings().getPrefix() + " " + s));
        }
    }
}
