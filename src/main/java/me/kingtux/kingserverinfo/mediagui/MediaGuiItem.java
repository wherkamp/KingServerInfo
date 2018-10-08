package me.kingtux.kingserverinfo.mediagui;

import me.kingtux.kingserverinfo.utils.JsonManager;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.inventory.ItemStack;

public class MediaGuiItem {

    private int position;
    private TextComponent link;
    private ItemStack item;
    private Boolean clickable;


    public MediaGuiItem(int position, ItemStack itemStack, String link, String beforeLinkMessage,
                        Boolean clickable) {
        this.position = position;
        item = itemStack;
        this.link = JsonManager.makeLinkText(beforeLinkMessage, link);
        this.clickable = clickable;
    }

    @Override
    public String toString() {
        return "Items{" +
                "position=" + position +
                ", link=" + link.toString() +
                ", item=" + item.toString() +
                ", clickable=" + clickable +
                '}';
    }


    public int getPosition() {
        return position;
    }

    public TextComponent getLink() {
        return link;
    }

    public Boolean isClickable() {
        return clickable;
    }

    public ItemStack getItem() {
        return item;
    }
}
