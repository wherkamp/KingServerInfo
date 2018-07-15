package me.kingtux.kingserverinfo.utils.mediagui;

import java.util.ArrayList;
import java.util.HashMap;
import me.kingtux.kingserverinfo.KingServerInfoMain;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MediaGui {

  private KingServerInfoMain plugin;
  private Inventory mediaGui;
  private HashMap<Items, ItemStack> guiItems = new HashMap<>();

  public MediaGui(KingServerInfoMain pl) {
    plugin = pl;
  }

  public Inventory createMediaGui() {
    mediaGui = Bukkit.createInventory(null, plugin.getConfigSettings().getMediaSize(),
        plugin.getConfigSettings().getGuiTitle());
    ArrayList<Items> allItems = plugin.getConfigSettings().getGuiItems();
    for (Items items : allItems) {
      mediaGui.setItem(items.getPosition(), items.getItem());
      guiItems.put(items, items.getItem());

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
