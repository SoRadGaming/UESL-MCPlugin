package com.soradgaming.ueslmcplugin;

import me.ryandw11.ultrachat.api.channels.ChatChannel;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldGaurdRegion extends JavaPlugin implements Listener {

    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event)
    {
        Player player = Bukkit.getPlayer(event.getUUID());
        if (player == null) return;

        String regionName = event.getRegionName();
        if(regionName !== null)
        {
            setPlayerChannel(player, regionName)
        }
    }

    public void setPlayerChannel(org.bukkit.entity.Player player,ChatChannel channel){

    }

}