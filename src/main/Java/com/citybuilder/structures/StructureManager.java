package com.citybuilder.structures;

import com.citybuilder.CityBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.Location;

import java.io.File;
import java.io.FileInputStream;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.operation.Operation;
import com.sk89q.worldedit.operation.Operations;

public class StructureManager {

    private static CityBuilder plugin;

    public static void init(CityBuilder pl) {
        plugin = pl;
        plugin.getLogger().info("StructureManager initialized.");
    }

    public static void paste(Player player, String name) {
        File schemFile = new File(plugin.getDataFolder(), "structures/" + name + ".schem");

        if (!schemFile.exists()) {
            player.sendMessage(ChatColor.RED + "Structure file not found: " + name + ".schem");
            return;
        }

        try (ClipboardReader reader = ClipboardFormats.findByFile(schemFile)
                .getReader(new FileInputStream(schemFile))) {

            Clipboard clipboard = reader.read();
            Location loc = player.getLocation();

            try (EditSession editSession = WorldEdit.getInstance()
                    .newEditSession(BukkitAdapter.adapt(player.getWorld()))) {

                Operation operation = new ClipboardHolder(clipboard)
                        .createPaste(editSession)
                        .to(BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()))
                        .ignoreAirBlocks(false)
                        .build();

                Operations.complete(operation);
                player.sendMessage(ChatColor.GREEN + "Structure pasted: " + name);

            }

        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "Error pasting structure: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
