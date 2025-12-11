package com.citybuilder;

import org.bukkit.plugin.java.JavaPlugin;
import com.citybuilder.commands.FillerCommand;
import com.citybuilder.structures.StructureManager;
import java.io.File; 

public class CityBuilder extends JavaPlugin {

    @Override
    public void onEnable() {
        // Log startup
        getLogger().info("CityBuilder has been enabled!");

        // Ensure plugin data folder exists
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        // Ensure structures folder exists inside plugin data folder
        File structuresFolder = new File(getDataFolder(), "structures");
        if (!structuresFolder.exists()) {
            structuresFolder.mkdirs();
            getLogger().info("Created structures folder at: " + structuresFolder.getPath());
        } else {
            getLogger().info("Structures folder ready at: " + structuresFolder.getPath());
        }

        // Register commands
        if (getCommand("fillerbuilding") != null) {
            getCommand("fillerbuilding").setExecutor(new FillerCommand());
        }

        // Initialize structure manager
        StructureManager.init(this);
    }

    @Override
    public void onDisable() {
        // Log shutdown
        getLogger().info("CityBuilder has been disabled!");
    }
}
