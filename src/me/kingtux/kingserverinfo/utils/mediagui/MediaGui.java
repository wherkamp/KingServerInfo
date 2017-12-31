package me.kingtux.kingserverinfo.utils.mediagui;

import me.kingtux.kingserverinfo.KingServerInfoMain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaGui {
    private KingServerInfoMain plugin;
    private Inventory mediaGui;
    private HashMap<Items, ItemStack> guiItems = new HashMap<>();

    public MediaGui(KingServerInfoMain pl) {
        plugin = pl;
    }

    public Inventory createMediaGui() {
        mediaGui = Bukkit.createInventory(null, plugin.getConfigSettings().getMediaSize(), plugin.getConfigSettings().getGuiTitle());
        ArrayList<Items> allItems = plugin.getConfigSettings().getGuiItems();
        for (Items items : allItems) {
            ItemStack itemStack;
            itemStack = new ItemStack(Material.getMaterial(items.getItemType()));
            ItemMeta itemsmeta = itemStack.getItemMeta();
            itemsmeta.setLore(items.getSubText());
            itemsmeta.setDisplayName(items.getItemName());
            itemStack.setItemMeta(itemsmeta);
            mediaGui.setItem(items.getPosition(), itemStack);
            guiItems.put(items, itemStack);

        }
        return mediaGui;
    }

    public Inventory getMediaGui() {
        return mediaGui;
    }

    public HashMap<Items, ItemStack> getguiItems() {
        return guiItems;
    }
}
