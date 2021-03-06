package me.kingtux.kingserverinfo.mediagui;

import me.kingtux.kingserverinfo.KingServerInfoMain;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaGui {

    private KingServerInfoMain plugin;
    private Inventory mediaGui;
    private HashMap<MediaGuiItem, ItemStack> guiItems = new HashMap<>();

    public MediaGui(KingServerInfoMain pl) {
        plugin = pl;
    }

    public Inventory createMediaGui() {
        mediaGui = Bukkit.createInventory(null, plugin.getConfigSettings().getMediaSize(),
                plugin.getConfigSettings().getGuiTitle());
        ArrayList<MediaGuiItem> allItems = plugin.getConfigSettings().getGuiItems();
        for (MediaGuiItem items : allItems) {
            mediaGui.setItem(items.getPosition(), items.getItem());
            guiItems.put(items, items.getItem());

        }
        return mediaGui;
    }

    public Inventory getMediaGui() {
        return mediaGui;
    }

    public HashMap<MediaGuiItem, ItemStack> getguiItems() {
        return guiItems;
    }
}
