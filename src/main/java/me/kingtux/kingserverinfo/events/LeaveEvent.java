package me.kingtux.kingserverinfo.events;

import java.util.List;
import me.kingtux.kingserverinfo.KingServerInfoMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {
    private KingServerInfoMain plugin;

    public LeaveEvent(KingServerInfoMain pl) {
        plugin = pl;
    }

    @EventHandler
    public void playerLeaveEvent(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        List<String> leaveMessage = plugin.getConfigSettings().getOnLeaveMessage();
        for (String s : leaveMessage) {
            s = s.replace("{player}", player.getName());
            Bukkit.getServer().broadcastMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfigSettings().getPrefix() + " " + s));
        }
    }
}
