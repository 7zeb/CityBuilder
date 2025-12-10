package com.citybuilder.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CityBuilderHelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage("§6=== CityBuilder Help ===");
        sender.sendMessage("§e/fillerbuilding <template> - Spawn a filler building");
        sender.sendMessage("§e/citybuilderabout - Info about the plugin");
        sender.sendMessage("/citybuilderhelp  - This shows the help information of the plugin");
        return true;
    }
}
