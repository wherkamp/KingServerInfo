package me.kingtux.kingserverinfo.commands;

import java.util.List;

public class Arguments {

  private String argument, description;
  private List<String> playerMessage, alias, broudcastMessage, consoleCommand, playerCommand;

  public Arguments(String argument, String description, List<String> alias,
      List<String> playerMessage,
      List<String> broudcastMessage,
      List<String> consoleCommand,
      List<String> playerCommand) {
    this.argument = argument;
    this.description = description;
    this.alias = alias;
    this.playerMessage = playerMessage;
    this.broudcastMessage = broudcastMessage;
    this.playerCommand = playerCommand;
    this.consoleCommand = consoleCommand;
  }


  public String getArgument() {
    return argument;
  }

  public List<String> getPlayerMessage() {
    return playerMessage;
  }

  public List<String> getAlias() {
    return alias;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getBroudcastMessage() {
    return broudcastMessage;
  }

  public List<String> getConsoleCommand() {
    return consoleCommand;
  }

  public List<String> getPlayerCommand() {
    return playerCommand;
  }
}
