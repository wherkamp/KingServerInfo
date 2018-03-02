package me.kingtux.kingserverinfo.commands

class Arguments(val argument: String, val description: String, val alias: List<String>,
                val playerMessage: List<String>,
                val broudcastMessage: List<String>,
                val consoleCommand: List<String>,
                val playerCommand: List<String>)
