package com.soradgaming.ueslmcplugin;

import com.soradgaming.ueslmcplugin.Commands.ConditionalEvents;
import com.soradgaming.ueslmcplugin.Handler.RegionChannel;
import com.soradgaming.ueslmcplugin.Handler.WorldChannel;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class UESLMCPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        if (Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
            getLogger().info("Hooked into WorldGuard");
        } else {
            throw new RuntimeException("Could not find WorldGuard! Plugin can not work without it!");
        }

        if (Bukkit.getPluginManager().isPluginEnabled("UltraChat")) {
            getLogger().info("Hooked into UltraChat");
        } else {
            throw new RuntimeException("Could not find UltraChat! Plugin can not work without it!");
        }

        if (Bukkit.getPluginManager().isPluginEnabled("WorldGuardEvents")) {
            getServer().getPluginManager().registerEvents(new RegionChannel(), this);
            getLogger().info("Hooked into WorldGuardEvents");
        } else {
            throw new RuntimeException("Could not find WorldGuardEvents! Plugin can not work without it!");
        }
        //EventHandler
        getServer().getPluginManager().registerEvents(new WorldChannel(), this);
        getServer().getPluginManager().registerEvents(new ConditionalEvents(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}