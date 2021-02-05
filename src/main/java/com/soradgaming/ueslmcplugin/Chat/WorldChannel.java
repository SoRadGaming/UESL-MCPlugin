package com.soradgaming.ueslmcplugin.Chat;

import me.ryandw11.ultrachat.api.UltraChatAPI;
import me.ryandw11.ultrachat.api.channels.ChatChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChannel implements Listener {

    public static UltraChatAPI chat = new UltraChatAPI();

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String worldFrom = event.getFrom().getName();
        String worldTo = player.getWorld().getName();

        if (worldTo.startsWith("factions") && !worldFrom.startsWith("factions")) {
            changeChannel(player, "factions");
        } else if (worldTo.equals("Creative")) {
            changeChannel(player, "creative");
        } else if (worldTo.startsWith("survival") && !worldFrom.startsWith("survival")) {
            changeChannel(player, "survival");
        } else if (worldTo.equals("Breeze2")) {
            changeChannel(player, "Breeze2");
        } else if ((worldTo.startsWith("IridiumSkyblock") && !worldFrom.startsWith("IridiumSkyblock") && !worldFrom.equals("SkyblockHub")) || (worldTo.equals("SkyblockHub") && !worldFrom.startsWith("IridiumSkyblock"))) {
            changeChannel(player, "IridiumSkyblock");
        } else if (worldTo.equals("PlanetParkour")) {
            changeChannel(player, "PlanetParkour");
        } else if (worldTo.equals("ParkourParadise")) {
            changeChannel(player, "ParkourParadise");
        }
    }

    public static void changeChannel(Player p, String channel) {
        ChatChannel c = chat.getChannelManager().getChannelByName(channel);
        chat.getChannelManager().setPlayerChannel(p, c);
    }
}
