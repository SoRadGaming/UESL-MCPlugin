package com.soradgaming.ueslmcplugin.ConditionalEvents;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.util.Objects;

public class PlayerWorldChange implements Listener {

    private UESLMCPlugin plugin;

    public PlayerWorldChange() {
        plugin = UESLMCPlugin.plugin;
    }

    @EventHandler
    public void PlayerWorldChangeEvent(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String worldFrom = event.getFrom().getName();
        String worldTo = player.getWorld().getName();

        //Half Heart PlanetParkour Fix
        if (worldFrom.equals("PlanetParkour")) {
            if (Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue() <= 2) {
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20);
                player.setHealth(20);
            }
        }

        //Parkour Join
        if (worldFrom.equals("World")) {
            if (player.getWorld().toString().equals("ParkourParadise")) {
                if (plugin.data.getBoolean(Objects.requireNonNull(player).getUniqueId().toString() + ".parkourparadise_completed")) {
                    player.sendMessage("You have already claimed the prize for this course!");
                }
            } else if (player.getWorld().toString().equals("PlanetParkour")) {
                if (plugin.data.getBoolean(Objects.requireNonNull(player).getUniqueId().toString() + ".planetparkour_completed")) {
                    player.sendMessage("You have already claimed the prize for this course!");
                }
            }
            player.sendMessage("Use /minigames to leave the course and return to minigames");
        }
    }
}
