package me.kingtux.kingserverinfo.events;

import me.kingtux.kingserverinfo.KingServerInfoMain;
import me.kingtux.kingserverinfo.mediagui.MediaGuiItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickEvent implements Listener {

    private KingServerInfoMain plugin;

    public ClickEvent(KingServerInfoMain pl) {
        plugin = pl;
    }

    @EventHandler
    public void playerClickEvent(InventoryClickEvent e) {
        if (e.getClickedInventory().equals(plugin.getMediaGui().getMediaGui())) {
            for (MediaGuiItem item : plugin.getMediaGui().getguiItems().keySet()) {
                if (e.getCurrentItem().isSimilar(plugin.getMediaGui().getguiItems().get(item))) {
                    if (item.isClickable()) {
                        e.getWhoClicked().spigot().sendMessage(item.getLink());
                        e.getWhoClicked().closeInventory();
                        e.setCancelled(true);
                        break;
                    } else {
                        e.setCancelled(true);
                        break;
                    }

                }
            }


        }

    }
}
