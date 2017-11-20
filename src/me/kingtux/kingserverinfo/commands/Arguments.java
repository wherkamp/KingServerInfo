package me.kingtux.kingserverinfo.commands;

import java.util.List;

public class Arguments {
    private String arugment, description;
    private List<String> message, alias;

    public Arguments(String argument, List<String> message, List<String> alias, String description) {
        this.arugment = argument;
        this.message = message;
        this.alias = alias;
        this.description = description;
    }

    public Arguments(String argument, List<String> message, String description) {
        this.arugment = argument;
        this.message = message;
        this.description = description;
    }

    public String getArugment() {
        return arugment;
    }

    public List<String> getMessage() {
        return message;
    }

    public List<String> getAlias() {
        return alias;
    }

    public String getDescription() {
        return description;
    }
}
