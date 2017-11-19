package me.kingtux.kingserverinfo.utils.MediaGui;

import me.kingtux.kingserverinfo.utils.JsonManager;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;

public class Items {
    private int position;
    private TextComponent link;
    private String name, itemName, itemType;
    private Boolean clickable;
    private List<String> subText;

    public Items(int position, String name, String itemName, String itemType, String link, Boolean clickable, List<String> subText) {
        this.position = position;
        this.name = name;
        this.itemName = itemName;
        this.itemType = itemType.toUpperCase();
        this.link = JsonManager.MakeLinkText(link);
        this.clickable = clickable;
        this.subText = subText;

    }

    public int getPosition() {
        return position;
    }

    public TextComponent getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public Boolean getClickable() {
        return clickable;
    }

    public List<String> getSubText() {
        return subText;
    }

    @Override
    public String toString() {
        return "Items{" +
                "position=" + position +
                ", link=" + link +
                ", name='" + name + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemType='" + itemType + '\'' +
                ", clickable=" + clickable +
                ", subText=" + subText +
                '}';
    }
}
