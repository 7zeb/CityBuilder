package com.citybuilder.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CityBuilderAboutCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage("§6=== CityBuilder Plugin ===");
        sender.sendMessage("§eVersion: §f1.2.1");
        sender.sendMessage("§eAuthor: §f7Zeb");
        sender.sendMessage("§eDescription: §fA Spigot plugin for spawning filler buildings like stores, apartments, warehouses, and skyscrapers.");
        sender.sendMessage("§eWebsite: §fhttps://github.com/7Zeb/CityBuilder");
        sender.sendMessage("§6===========================");
        return true;
    }
}
