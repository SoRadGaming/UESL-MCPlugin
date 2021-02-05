package com.soradgaming.ueslmcplugin.Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

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
            e.printStackTrace();
            return null;
        }
    }
}
 