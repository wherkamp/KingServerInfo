package me.kingtux.kingserverinfo.events;

import me.clip.placeholderapi.PlaceholderAPI;
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
        PlaceholderAPI api = new PlaceholderAPI();
        Player player = e.getPlayer();
        List<String> onJoinBroudcastMessage = plugin.getConfigSettings().getOnJoinBroudcastMessage();
        for (String s : onJoinBroudcastMessage) {
            String PlaceHolderMessage = PlaceholderAPI.setPlaceholders(player, s);
            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfigSettings().getPrefix() + " " + PlaceHolderMessage));
        }
        List<String> onJoinPersonalMessage = plugin.getConfigSettings().getOnJoinPersonalMessage();
        for (String s : onJoinPersonalMessage) {
            String PlaceHolderMessage = PlaceholderAPI.setPlaceholders(player, s).toString();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfigSettings().getPrefix() + " " + PlaceHolderMessage));
        }
    }
}
