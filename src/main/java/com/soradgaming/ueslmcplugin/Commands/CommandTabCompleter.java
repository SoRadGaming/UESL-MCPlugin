package com.soradgaming.ueslmcplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command cmd, @NotNull String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("uesl")){
            ArrayList<String> completions = new ArrayList<>();
            if (args.length == 1) {
                completions = new ArrayList<>(Arrays.asList("data", "help", "reload"));
                completions = getApplicableTabCompletes(args[0], completions);
            } else {
                if(args[0].equalsIgnoreCase("data")) {
                    completions = new ArrayList<>(Collections.singletonList("edit"));
                    completions = getApplicableTabCompletes(args.length == 2 ? args[1] : "", completions);
                } else {
                    if (args[1].equalsIgnoreCase("edit")) {
                        completions = new ArrayList<>();
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            completions.add(player.toString());
                        }
                        completions = getApplicableTabCompletes(args.length == 3 ? args[2] : "", completions);
                    } else {
                        if (args[2].equalsIgnoreCase(String.valueOf(completions))) {
                            completions = new ArrayList<>(Arrays.asList("parkourparadise_completed", "planetparkour_completed"));
                            completions = getApplicableTabCompletes(args.length == 4 ? args[3] : "", completions);
                        } else {
                            if (args[3].equalsIgnoreCase("parkourparadise_completed") || args[3].equalsIgnoreCase("planetparkour_completed")) {
                                completions = new ArrayList<>(Arrays.asList("true", "false"));
                                completions = getApplicableTabCompletes(args.length == 5 ? args[4] : "", completions);
                            }
                        }
                    }
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
