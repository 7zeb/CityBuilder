package com.citybuilder.structures;

import com.citybuilder.CityBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.Location;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.ClipboardHolder;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.session.EditSession;
import com.sk89q.worldedit.math.BlockVector3;

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

        ClipboardFormat format = ClipboardFormats.findByFile(schemFile);
        if (format == null) {
            player.sendMessage(ChatColor.RED + "Unknown schematic format: " + schemFile.getName());
            return;
        }

        try (ClipboardReader reader = format.getReader(new FileInputStream(schemFile))) {
            Clipboard clipboard = reader.read();
            Location loc = player.getLocation();

            try (EditSession editSession = WorldEdit.getInstance()
                    .newEditSession(BukkitAdapter.adapt(player.getWorld()))) {

                new ClipboardHolder(clipboard)
                        .createPaste(editSession)
                        .to(BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()))
                        .ignoreAirBlocks(false)
                        .build()
                        .execute(); // <-- direct execution in WorldEdit 7.3.0

                player.sendMessage(ChatColor.GREEN + "Structure pasted: " + name);
            }

        } catch (IOException e) {
            player.sendMessage(ChatColor.RED + "Error reading schematic: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "Error pasting structure: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
