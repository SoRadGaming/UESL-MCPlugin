package com.soradgaming.ueslmcplugin;

import me.ryandw11.ultrachat.api.UltraChatAPI;
import me.ryandw11.ultrachat.api.channels.ChatChannel;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import java.lang.String;
import java.nio.channels.Channel;

public class RegionChannelHandler implements Listener {
    public static UltraChatAPI chat = new UltraChatAPI();

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        assert player != null;
        String name = player.getName();
        String regionName = event.getRegionName();

        if(!regionName.equals("factions_spawn")) {
            changeChannel(name, regionName);
        }
    }

    public static void changeChannel(String name, String channel) {
        Player player = Bukkit.getServer().getPlayer(name);
        Channel c = (Channel) chat.getChannelManager().getChannelByName(channel);
        chat.getChannelManager().setPlayerChannel(player, (ChatChannel) c);
    }
}

