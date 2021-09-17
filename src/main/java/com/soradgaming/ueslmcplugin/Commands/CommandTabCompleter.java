package com.soradgaming.ueslmcplugin.Commands;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import me.ryandw11.ultrachat.api.UltraChatAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CommandTabCompleter implements TabCompleter {

    private final UESLMCPlugin plugin;

    public CommandTabCompleter() {
        plugin = UESLMCPlugin.plugin;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command cmd, @NotNull String s, String[] args) {
        List<String> parkour = plugin.getConfig().getStringList("Parkour");
        if(cmd.getName().equalsIgnoreCase("uesl")){
            ArrayList<String> completions = new ArrayList<>();
            if (args.length == 1) {
                completions = new ArrayList<>(Arrays.asList("data", "help", "reload", "edit"));
                completions = getApplicableTabCompletes(args[0], completions);
            } else if (args.length == 2) {
                    if (args[0].equals("data") || args[0].equals("edit")) {
                        completions = new ArrayList<>();
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            completions.add(player.getName());
                        }
                        completions = getApplicableTabCompletes(args[1], completions);
                    } else {
                        return null;
                    }
                } else if (args.length == 3) {
                    if (args[0].equals("edit")) {
                        completions = (ArrayList<String>) parkour;
                        completions = getApplicableTabCompletes(args[2], completions);
                    } else {
                        return null;
                    }
                } else if (args.length == 4) {
                    if (parkour.contains(args[2])) {
                        completions = new ArrayList<>(Arrays.asList("true", "false"));
                        completions = getApplicableTabCompletes(args[3], completions);
                    } else {
                        return null;
                    }
                }
            Collections.sort(completions);
            return completions;
        }
        return null;
    }

    public ArrayList<String> getApplicableTabCompletes(String arg, ArrayList<String> completions) {
        if (arg == null || arg.equalsIgnoreCase("")) {
            return completions;
        }
        ArrayList<String> valid = new ArrayList<>();
        for (String possibly : completions) {
            if (possibly.startsWith(arg)) {
                valid.add(possibly);
            }
        }
        return valid;
    }
}
