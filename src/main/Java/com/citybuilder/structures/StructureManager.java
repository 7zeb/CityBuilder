package com.citybuilder.structures;

import com.citybuilder.CityBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class StructureManager {

    private static CityBuilder plugin;
    private static final Map<String, String> structureMap = new HashMap<>();

    //Initialize with plugin reference
    public static void init(CityBuilder pl) {
        plugin = pl;

        //Register default structures
        register("store", "store.schem");
        register("apartment", "apartment.schem");
        register("warehouse", "warehouse.schem");
        register("rc:skyscraper_4", "skyscraper_4.schem");

        plugin.getLogger().info("StructureManager initialized with " + structureMap.size() + " structures.");
    }

    //Register a structure name → file mapping
    public static void register(String name, String fileName) {
        structureMap.put(name.toLowerCase(), fileName);
    }

    //Paste a structure at player location
    public static void paste(Player player, String name) {
        String fileName = structureMap.get(name.toLowerCase());
        if (fileName == null) {
            player.sendMessage(ChatColor.RED + "Unknown structure: " + name);
            return;
        }

        File schemFile = new File(plugin.getDataFolder(), "structures/" + fileName);
        if (!schemFile.exists()) {
            player.sendMessage(ChatColor.RED + "Structure file not found: " + fileName);
            return;
        }

        // TODO: integrate WorldEdit API here
        // Example: load schematic and paste at player.getLocation()
        player.sendMessage(ChatColor.GREEN + "Pasting structure: " + name + " at your location...");
    }
}
