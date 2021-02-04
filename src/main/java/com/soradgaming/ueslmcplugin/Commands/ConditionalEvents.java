package com.soradgaming.ueslmcplugin.Commands;

import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.lang.String;
import java.util.ArrayList;

//Conditional Events
public class ConditionalEvents implements Listener  {
    public ArrayList<Player> planetparkour_completed = new ArrayList<>();
    public ArrayList<Player> parkourparadise_completed = new ArrayList<>();

    //Event WorldGuard Region Enter
    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        String regionName = event.getRegionName();
        String p = event.getPlayer().getName();

        //Skyblock_Portal
        if (regionName.equals("skyblock_portal_1") || regionName.equals("skyblock_portal_2") || regionName.equals("skyblock_portal_3") || regionName.equals("skyblock_portal_4") || regionName.equals("skyblock_portal_5") || regionName.equals("skyblock_portal_6")) {
            player.performCommand("is home");
            player.sendMessage("Welcome to Skyblocks!");
        }
        //Skyblock_Death_Loop_Fix
        if (regionName.equals("skyblock_death_box")) {
            player.performCommand("is home"); // event not activated on respawn
        }
        //Dues Gamemode
        if (regionName.equals("duels_arena")) {
            if (player.getGameMode() != GameMode.SPECTATOR) {
                player.setGameMode(GameMode.ADVENTURE);
            }
        }
        //SecretHub Parkour Finish
        if (regionName.equals("shub_portal")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p + " permission set essentials.warps.secrethub true");
            player.sendTitle("Congratulations" + ChatColor.BLUE + ChatColor.BOLD, "", 10, 70, 20);
            player.sendMessage("Use /shub to get here in the future");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp secrethub " + p);
        }
        //ParkourParadise Portal
        if (regionName.equals("parkourparadise_portal")) {
            player.performCommand("pa join parkourparadise");
        }
        //Planet Parkour End
        if (regionName.equals("planetparkour_end")) {
            if(!planetparkour_completed.contains(player)){
                planetparkour_completed.add(player);
                player.sendTitle("Congratulations" + ChatColor.BLUE + ChatColor.BOLD, "", 10, 70, 20);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "procosmetics give coins " + p + " 250");
                player.sendMessage("You have won $500,000 for completing PlanetParkour!");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p + " 500000");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"You have won 250 &eUESL Points&r for completing PlanetParkour!"));
            } else player.sendMessage("You have already claimed this prize before!");
        }
        //Parkour Paradise End
        if (regionName.equals("parkourparadise_end")) {
            if (!parkourparadise_completed.contains(player)) {
                parkourparadise_completed.add(player);
                player.sendMessage("You completed Parkour Paradise");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "procosmetics give coins " + p + " 250");
                player.sendMessage("You have won $500,000 for completing ParkourParadise!");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p + " 500000");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "You have won 250 &eUESL Points&r for completing ParkourParadise!"));
            } else player.sendMessage("You have already claimed this prize before!");
        }
    }

    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        String world = player.getWorld().toString();

        if (world.equals("IridiumSkyblock_nether") || world.equals("IridiumSkyblock")) {
            if (event.isBedSpawn() || event.isAnchorSpawn()) {
            } else player.performCommand("is home");
        }
    }
}