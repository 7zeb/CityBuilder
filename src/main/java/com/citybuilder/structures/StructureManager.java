package com.citybuilder.structures;

import com.citybuilder.CityBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.WorldEditException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class StructureManager {

    private static CityBuilder plugin;

    public static void init(CityBuilder pl) {
        plugin = pl;
    }

    public static void paste(Player player, String name) {
        File structuresFolder = new File(plugin.getDataFolder(), "structures");
        File schematicFile = new File(structuresFolder, name + ".schem");

        if (!schematicFile.exists()) {
            player.sendMessage(ChatColor.RED + "Schematic not found: " + name);
            return;
        }

        ClipboardFormat format = ClipboardFormats.findByFile(schematicFile);
        if (format == null) {
            player.sendMessage(ChatColor.RED + "Unknown schematic format for: " + name);
            return;
        }

        try (FileInputStream fis = new FileInputStream(schematicFile);
             ClipboardReader reader = format.getReader(fis)) {

            Clipboard clipboard = reader.read();

            try (EditSession editSession = WorldEdit.getInstance()
                    .newEditSession(BukkitAdapter.adapt(player.getWorld()))) {

                Operation operation = new ClipboardHolder(clipboard)
                        .createPaste(editSession)
                        .to(BukkitAdapter.asBlockVector(player.getLocation()))
                        .ignoreAirBlocks(false)
                        .build();

                try {
                    Operations.complete(operation);
                    player.sendMessage(ChatColor.GREEN + "Pasted schematic: " + name);
                } catch (WorldEditException e) {
                    player.sendMessage(ChatColor.RED + "Error pasting schematic: " + e.getMessage());
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            player.sendMessage(ChatColor.RED + "Error loading schematic: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
