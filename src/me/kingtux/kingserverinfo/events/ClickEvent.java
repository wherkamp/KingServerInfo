package me.kingtux.kingserverinfo.events;


import me.kingtux.kingserverinfo.KingServerInfoMain;
import me.kingtux.kingserverinfo.utils.MediaGui.Items;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickEvent implements Listener {
    private KingServerInfoMain plugin;

    public ClickEvent(KingServerInfoMain pl) {
        plugin = pl;
    }

    @EventHandler
    public void ClickEvent(InventoryClickEvent e) {
        System.out.println("Click Happend");

        if (e.getClickedInventory().equals(plugin.getMediaGui().getMediaGui())) {
            System.out.println("Click Gui FOund");

            for (Items item : plugin.getMediaGui().getguiItems().keySet()) {
                System.out.println("Click Items");

                if (e.getCurrentItem().equals(plugin.getMediaGui().getguiItems().get(item))) {
                    e.getWhoClicked().spigot().sendMessage(item.getLink());
                    System.out.println("Click Found");
                    e.setCancelled(true);
                    break;
                }
            }


        }

    }
}
