package me.kingtux.kingserverinfo.commands;

import java.util.List;

public class Arguments {
    private String argument, description;
    private List<String> message, alias;

    public Arguments(String argument, List<String> message, List<String> alias, String description) {
        this.argument = argument;
        this.message = message;
        this.alias = alias;
        this.description = description;
    }

    public Arguments(String argument, List<String> message, String description) {
        this.argument = argument;
        this.message = message;
        this.description = description;
    }

    public String getArgument() {
        return argument;
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
