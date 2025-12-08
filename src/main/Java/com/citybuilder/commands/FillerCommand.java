package com.citybuilder.commands;

import com.citybuilder.structures.StructureManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class FillerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Only players can run this command
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        // Permission check
        if (!player.hasPermission("citybuilder.filler.build")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        // Require at least one argument
        if (args.length == 0) {
            player.sendMessage(ChatColor.YELLOW + "Usage: /fillerbuilding <template>");
            return true;
        }

        String template = args[0]; // e.g. "apartment" or "store"

        // Hand off to StructureManager
        StructureManager.paste(player, template);

        return true;
    }
}
