package com.soradgaming.ueslmcplugin.Handler;

import me.ryandw11.ultrachat.api.UltraChatAPI;
import me.ryandw11.ultrachat.api.channels.ChatChannel;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.lang.String;

public class RegionChannel implements Listener {
    public static UltraChatAPI chat = new UltraChatAPI();

    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        String regionName = event.getRegionName();

        if (regionName.equals("hub")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("minigames")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("shub")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("hglobby")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("duels_arena")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("spleef")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("build_battle")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("tntrun")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("paintball")) {
            changeChannel(player, regionName);
        }
    }

    public static void changeChannel(Player p, String channel) {
        ChatChannel c = chat.getChannelManager().getChannelByName(channel);
        chat.getChannelManager().setPlayerChannel(p, c);
    }
}