package me.kingtux.kingserverinfo.events;


import me.kingtux.kingserverinfo.KingServerInfoMain;
import me.kingtux.kingserverinfo.utils.mediagui.Items;
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
            for (Items item : plugin.getMediaGui().getguiItems().keySet()) {
                if (e.getCurrentItem().equals(plugin.getMediaGui().getguiItems().get(item))) {
                    if (item.getClickable()) {
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
