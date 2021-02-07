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
                completions = new ArrayList<>(Arrays.asList("data", "help", "reload", "edit"));
                completions = getApplicableTabCompletes(args[0], completions);
            } else {
                if(args[0].equalsIgnoreCase("data") || args[0].equalsIgnoreCase("edit")) {
                    completions = new ArrayList<>();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        completions.add(player.toString());
                    }
                    completions = getApplicableTabCompletes(args.length == 2 ? args[1] : "", completions);
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
