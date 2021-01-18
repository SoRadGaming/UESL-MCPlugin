package com.soradgaming.ueslmcplugin;

import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.lang.String;

public class RegionChannelHandler implements Listener {

    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        assert player != null;
        String name = player.getName();
        String regionName = event.getRegionName();

        if(!regionName.equals("factions_spawn")) {
            WorldChannelHandler.changeChannelNew(name, regionName);
            WorldChannelHandler.changeChannelNew(name, regionName);
        }
    }
}