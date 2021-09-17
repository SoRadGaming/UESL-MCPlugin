package com.soradgaming.ueslmcplugin.Parkour;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.util.List;
import java.util.Objects;

public class Parkour implements Listener {

    private final UESLMCPlugin plugin;

    public Parkour() {plugin = UESLMCPlugin.plugin;}

    /*
    List<String> parkour = plugin.getConfig().getStringList("Parkour");
    for (String name : parkour) {
        String parkour_name = plugin.parkour.getString(name + ".parkour_name");
        String data_name = plugin.parkour.getString(name + ".data_name");
        String region_finish_name = plugin.parkour.getString(name + ".region_finish_name");
        String world_name = plugin.parkour.getString(name + ".world_name");
        int uesl_coin_prize = plugin.parkour.getInt(name + ".uesl_coin_prize");
        int money_prize = plugin.parkour.getInt(name + ".money_prize");
    }
     */

    //Finish Parkour
    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        String regionName = event.getRegionName();
        String p = Objects.requireNonNull(event.getPlayer()).getName();
        List<String> parkour = plugin.getConfig().getStringList("Parkour");

        for (String name : parkour) {
            String parkour_name = plugin.parkour.getString(name + ".parkour_name");
            String data_name = plugin.parkour.getString(name + ".data_name");
            String region_finish_name = plugin.parkour.getString(name + ".region_finish_name");
            int uesl_coin_prize = plugin.parkour.getInt(name + ".uesl_coin_prize");
            int money_prize = plugin.parkour.getInt(name + ".money_prize");

            if (regionName.equals(region_finish_name) && !plugin.data.getBoolean(Objects.requireNonNull(player).getUniqueId().toString() + "." + data_name)) {
                plugin.data.set(player.getUniqueId().toString() + "." + data_name, true);
                plugin.saveFile();
                player.sendMessage("You completed " + parkour_name);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "procosmetics give coins " + p + " " + uesl_coin_prize);
                player.sendMessage("You have won $" + money_prize + " for completing " + parkour_name + "!");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p + " " + money_prize);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "You have won " + uesl_coin_prize + " &eUESL Points&r for completing " + parkour_name + "!"));
            } else if (regionName.equals(region_finish_name)) {
                player.sendMessage("You completed " + parkour_name);
                player.sendMessage("You have already claimed this prize!");
            }
        }
    }

    //WorldChange / Start Parkour
    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String worldTo = player.getWorld().getName();
        List<String> parkour = plugin.getConfig().getStringList("Parkour");

        //Parkour Join World Message
        for (String name : parkour) {
            String data_name = plugin.parkour.getString(name + ".data_name");
            String world_name = plugin.parkour.getString(name + ".world_name");

            if (worldTo.equals(world_name) && plugin.data.getBoolean(Objects.requireNonNull(player).getUniqueId().toString() + "." + data_name)) {
                player.sendMessage("You have already claimed the prize for this course!");
                player.sendMessage("Use " + ChatColor.GREEN +  "/minigames " + ChatColor.WHITE + "to leave the course and return to minigames");
                return;
            } else if (worldTo.equals(world_name)) {
                player.sendMessage("Use " + ChatColor.GREEN +  "/minigames " + ChatColor.WHITE + "to leave the course and return to minigames");
            }
        }
    }
}
