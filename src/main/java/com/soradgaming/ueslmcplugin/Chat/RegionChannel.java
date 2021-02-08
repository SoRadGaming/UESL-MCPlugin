package com.soradgaming.ueslmcplugin.Chat;

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

        //Channel Event
        switch (regionName) {
            case "hub":
            case "minigames":
            case "shub":
            case "hglobby":
            case "duels_arena":
            case "spleef":
            case "build_battle":
            case "castle-defenders":
            case "tntrun":
            case "paintball":
                changeChannel(player, regionName);
                break;
        }
    }

    public static void changeChannel(Player p, String channel) {
        ChatChannel c = chat.getChannelManager().getChannelByName(channel);
        chat.getChannelManager().setPlayerChannel(p, c);
    }
}