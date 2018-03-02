package me.kingtux.kingserverinfo.utils.mediagui

import me.kingtux.kingserverinfo.KingServerInfoMain
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*

class MediaGui(private val plugin: KingServerInfoMain) {
    var mediaGui: Inventory? = null
        private set
    private val guiItems = HashMap<Items, ItemStack>()

    fun createMediaGui(): Inventory {
        mediaGui = Bukkit.createInventory(null, plugin.configSettings!!.mediaSize, plugin.configSettings!!.guiTitle)
        val allItems = plugin.configSettings!!.guiItems
        for (items in allItems!!) {
            mediaGui!!.setItem(items.position, items.item)
            guiItems[items] = items.item

        }
        return mediaGui!!
    }

    fun getguiItems(): HashMap<Items, ItemStack> {
        return guiItems
    }
}
