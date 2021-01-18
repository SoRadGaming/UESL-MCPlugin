package com.soradgaming.ueslmcplugin;

import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.String;

public class WorldGuardRegion extends JavaPlugin implements Listener {

    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event)
    {
        Player player = Bukkit.getPlayer(event.getUUID());
        if (player == null) return;

        String regionName = event.getRegionName();
        if(!regionName.contains("factions") && !regionName.contains("survival") && !regionName.contains("Creative") && !regionName.contains("IridiumSkyblock") && !regionName.contains("PlanetParkour") && !regionName.contains("ParkourParadise") && !regionName.contains("Breeze2")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set ultrachat.channel true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "channel " + regionName);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set ultrachat.channel false");
        }
    }
}