package com.citybuilder;

import org.bukkit.plugin.java.JavaPlugin;
import com.citybuilder.commands.FillerCommand;

public class CityBuilder extends JavaPlugin {

    @Override
    public void onEnable() {
        // Log startup
        getLogger().info("CityBuilder has been enabled!");

        // Register commands
        if (getCommand("fillerbuilding") != null) {
            getCommand("fillerbuilding").setExecutor(new FillerCommand());
        }
         StructureManager.init(this);
    }

    @Override
    public void onDisable() {
        // Log shutdown
        getLogger().info("CityBuilder has been disabled!");
    }
}
