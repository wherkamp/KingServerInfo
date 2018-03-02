package me.kingtux.kingserverinfo.events

import me.kingtux.kingserverinfo.KingServerInfoMain
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class ClickEvent(private val plugin: KingServerInfoMain) : Listener {

    @EventHandler
    fun playerClickEvent(e: InventoryClickEvent) {
        if (e.clickedInventory == plugin.mediaGui!!.mediaGui) {
            for (item in plugin.mediaGui!!.getguiItems().keys) {
                if (e.currentItem.isSimilar(plugin.mediaGui!!.getguiItems()[item])) {
                    if (item.isClickable!!) {
                        e.whoClicked.spigot().sendMessage(item.link)
                        e.whoClicked.closeInventory()
                        e.isCancelled = true
                        break
                    } else {
                        e.isCancelled = true
                        break
                    }

                }
            }


        }

    }
}
