package com.soradgaming.ueslmcplugin.Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;


public class Data implements Serializable {
    private static transient final long serialVersionUID = 5354882099165933008L;

    public final HashSet<UUID> previouslyWonPlayers;

    // Saving
    public Data(HashSet<UUID> previouslyWonPlayers) {
        this.previouslyWonPlayers = previouslyWonPlayers;
    }

    // Loading
    public Data(Data loadedData) {
        this.previouslyWonPlayers = loadedData.previouslyWonPlayers;
    }

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public static Data loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            Data data = (Data) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    public static void getBlocksPlayersAndSave() {
        // HashSet used for storing the online players
        HashSet<UUID> previouslyWonPlayers = new HashSet<UUID>();
        // We will first retrieve all the currently online players
        Bukkit.getServer().getOnlinePlayers().forEach(player -> previouslyWonPlayers.add(player.getUniqueId()));

        // Finally we save the retrieved data to a file
        new Data(previouslyWonPlayers).saveData("plugins/UESL-MCPlugin/playerdata/exampleplayer.data");
        Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
    }
    public static void welcomePlayersAndResetBlocks() {
        // Load the data from disc using our loadData method.
        Data data = new Data(Data.loadData("Tutorial.data"));
        // Change all of the blocks around the spawn to what we have saved in our file.
        Bukkit.getServer().getLogger().log(Level.INFO, "Data loaded");
    }
}
 