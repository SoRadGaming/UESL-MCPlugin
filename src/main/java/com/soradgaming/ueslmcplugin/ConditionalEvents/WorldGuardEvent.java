package com.soradgaming.ueslmcplugin.ConditionalEvents;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.lang.String;
import java.util.Objects;

//World Guard Events
public class WorldGuardEvent implements Listener  {

    private final UESLMCPlugin plugin;

    public WorldGuardEvent() {
        plugin = UESLMCPlugin.plugin;
    }

    public static String PlayerRegion;

    //Event WorldGuard Region Enter for Parkour
    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        String regionName = event.getRegionName();
        String p = Objects.requireNonNull(event.getPlayer()).getName();
        PlayerRegion = event.getRegionName();

        //Planet Parkour End (Database Update)
        if (regionName.equals("planetparkour_end")) {
            if(!plugin.data.getBoolean(Objects.requireNonNull(player).getUniqueId().toString() + ".planetparkour_completed")) {
                plugin.data.set(player.getUniqueId().toString() + ".planetparkour_completed", true);
                plugin.saveFile();
                player.sendTitle( ChatColor.BLUE + "" + "Congratulations", "", 10, 70, 20);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "procosmetics give coins " + p + " 250");
                player.sendMessage("You have won $500,000 for completing PlanetParkour!");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p + " 500000");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"You have won 250 &eUESL Points&r for completing PlanetParkour!"));
            } else {
                player.sendMessage("You have already claimed this prize!");
            }
        }
        //Parkour Paradise End (Database Update)
        if (regionName.equals("parkourparadise_end")) {
            if (!plugin.data.getBoolean(Objects.requireNonNull(player).getUniqueId().toString() + ".parkourparadise_completed")) {
                plugin.data.set(player.getUniqueId().toString() + ".parkourparadise_completed", true);
                plugin.saveFile();
                player.sendMessage("You completed Parkour Paradise");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "procosmetics give coins " + p + " 250");
                player.sendMessage("You have won $500,000 for completing ParkourParadise!");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p + " 500000");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "You have won 250 &eUESL Points&r for completing ParkourParadise!"));
            } else {
                player.sendMessage("You have already claimed this prize!");
            }
        }
    }
}
