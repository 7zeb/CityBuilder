package com.citybuilder.commands;

import com.citybuilder.CityBuilder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;

public class FillerListCommand implements CommandExecutor {

    private final CityBuilder plugin;

    public FillerListCommand(CityBuilder plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        File structuresFolder = new File(plugin.getDataFolder(), "structures");
        String[] files = structuresFolder.list((dir, name) -> name.toLowerCase().endsWith(".schem"));

        if (files == null || files.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "The plugin is corrupted, please re-install the plugin.");
            return true;
        }

        sender.sendMessage(ChatColor.GREEN + "Available schematics:");
        Arrays.stream(files).forEach(f ->
            sender.sendMessage(ChatColor.AQUA + "- " + f.replace(".schem", ""))
        );

        return true;
    }
}
