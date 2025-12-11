package com.citybuilder.commands;

import com.citybuilder.CityBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FillerTabCompleter implements TabCompleter {

    private final CityBuilder plugin;

    public FillerTabCompleter(CityBuilder plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        // Only suggest for the first argument (schematic name)
        if (cmd.getName().equalsIgnoreCase("fillerbuilding") && args.length == 1) {
            File structuresFolder = new File(plugin.getDataFolder(), "structures");
            String[] files = structuresFolder.list((dir, name) -> name.toLowerCase().endsWith(".schem"));

            if (files != null) {
                for (String f : files) {
                    String baseName = f.replace(".schem", "");
                    if (baseName.toLowerCase().startsWith(args[0].toLowerCase())) {
                        completions.add(baseName);
                    }
                }
            }
        }

        return completions;
    }
}
