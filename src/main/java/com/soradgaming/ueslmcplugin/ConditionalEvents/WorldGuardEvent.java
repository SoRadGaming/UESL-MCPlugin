package com.soradgaming.ueslmcplugin.ConditionalEvents;

import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.String;
import java.util.ArrayList;
import java.util.Objects;

//World Guard Events
public class WorldGuardEvent extends JavaPlugin implements Listener  {
    public ArrayList<Player> planetparkour_completed = new ArrayList<>();
    public ArrayList<Player> parkourparadise_completed = new ArrayList<>();

    //Event WorldGuard Region Enter
    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        String regionName = event.getRegionName();
        String p = Objects.requireNonNull(event.getPlayer()).getName();

        //Skyblock_Portal
        if (regionName.equals("skyblock_portal_1") || regionName.equals("skyblock_portal_2") || regionName.equals("skyblock_portal_3") || regionName.equals("skyblock_portal_4") || regionName.equals("skyblock_portal_5") || regionName.equals("skyblock_portal_6")) {
            assert player != null;
            player.performCommand("is home");
            player.sendMessage("Welcome to Skyblocks!");
        }
        //Skyblock_Death_Loop_Fix
        if (regionName.equals("skyblock_death_box") || regionName.equals("skyblock_death_box_nether")) {
            assert player != null;
            player.performCommand("skyblocks");
        }
        //Dues Gamemode
        if (regionName.equals("duels_arena")) {
            assert player != null;
            if (player.getGameMode() != GameMode.SPECTATOR) {
                player.setGameMode(GameMode.ADVENTURE);
            }
        }
        //SecretHub Parkour Finish
        if (regionName.equals("shub_portal")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p + " permission set essentials.warps.secrethub true");
            assert player != null;
            player.sendTitle(ChatColor.BLUE + "" + ChatColor.BOLD + "Congratulations", "", 10, 70, 20);
            player.sendMessage("Use /shub to get here in the future");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp secrethub " + p);
        }
        //ParkourParadise Portal
        if (regionName.equals("parkourparadise_portal")) {
            assert player != null;
            player.performCommand("pa join parkourparadise");
        }
        //Planet Parkour End
        if (regionName.equals("planetparkour_end")) {
            if(!planetparkour_completed.contains(player)){
                planetparkour_completed.add(player);
                assert player != null;
                player.sendTitle( ChatColor.BLUE + "" + ChatColor.BOLD + "Congratulations", "", 10, 70, 20);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "procosmetics give coins " + p + " 250");
                player.sendMessage("You have won $500,000 for completing PlanetParkour!");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p + " 500000");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"You have won 250 &eUESL Points&r for completing PlanetParkour!"));
            } else {
                assert player != null;
                player.sendMessage("You have already claimed this prize!");
            }
        }
        //Parkour Paradise End
        if (regionName.equals("parkourparadise_end")) {
            if (!parkourparadise_completed.contains(player)) {
                parkourparadise_completed.add(player);
                assert player != null;
                player.sendMessage("You completed Parkour Paradise");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "procosmetics give coins " + p + " 250");
                player.sendMessage("You have won $500,000 for completing ParkourParadise!");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p + " 500000");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "You have won 250 &eUESL Points&r for completing ParkourParadise!"));
            } else {
                assert player != null;
                player.sendMessage("You have already claimed this prize!");
            }
        }
    }
}
