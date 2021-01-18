package com.soradgaming.ueslmcplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class UESLMCPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new RegionChannelHandler(), this);
        getServer().getPluginManager().registerEvents(new WorldChannelHandler(), this);
        // Plugin starup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}