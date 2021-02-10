package com.soradgaming.ueslmcplugin.Items;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.Objects;

public class ProCosmeticsChest implements Listener {

    private final UESLMCPlugin plugin;

    public ProCosmeticsChest() {
        plugin = UESLMCPlugin.plugin;
    }

    //Chest Click Usage
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player p = event.getPlayer();
            try {
                if (p.getInventory().getItemInMainHand().getType() == Material.CHEST && Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aCosmetic Menu &7(Right-click)"))) {
                    event.setCancelled(true);
                    p.performCommand("procosmetics open main");
                } else {
                    event.setCancelled(false);
                }
            }
            catch (Exception var3_3) {
                System.out.println("[UESL-MCPlugin] A open-chest error has been thrown. Please contact SoRadGaming if the problem persist.");
            }
        }
    }

    //Prevent Dropping the Chest
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        ItemStack chest = event.getItemDrop().getItemStack();
        if (chest.isSimilar(chest) && chest.hasItemMeta() && Objects.requireNonNull(chest.getItemMeta()).getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aCosmetic Menu &7(Right-click)"))) {
            event.setCancelled(true);
        }
    }

    //Give Chest on Join
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (p.getWorld().getName().equals("Lobbyy")) {
            ItemStack chest = this.Chest();
            if (!p.getInventory().contains(chest)) {
                p.getInventory().setItem(8, chest);
                System.out.println("[UESL-MCPlugin] " + p.getName() + " does not have a chest. Giving it now.");
            }
        } else {
            System.out.println("[UESL-MCPlugin] A player join error has been thrown. Please contact SoRadGaming if the problem persist.");
            System.out.println("[UESL-MCPlugin] Maybe player did not load in Hub?");
        }
    }

    //Chest Change World Handler
    @EventHandler (priority = EventPriority.MONITOR)
    public void onChangeWorld(RegionEnteredEvent event) {
        Player p = event.getPlayer();
        String region = event.getRegionName();
        ItemStack chest = this.Chest();
        assert p != null;
        boolean regiondata = region.equals("hub") || region.equals("shub") || region.equals("minigames") || region.equals("hglobby");

        if (regiondata && !p.getInventory().contains(this.Chest()) && !p.getInventory().getItemInOffHand().isSimilar(chest)) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                System.out.println("[UESL-MCPlugin] " + p.getName() + " does not have a chest. Giving it now.");
                p.getInventory().setItem(8, this.Chest());
            }, 500);
        }
        if (!regiondata && (p.getInventory().contains(this.Chest()) || p.getInventory().getItemInOffHand().isSimilar(chest))) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                System.out.println("[UESL-MCPlugin] " + p.getName() + " has entered a non-chest world. Removing compass or making it invalid now.");
                p.getInventory().removeItem(this.Chest());
            }, 500);
        }
    }

    //Chest Item
    public ItemStack Chest() {
        ItemStack chest = new ItemStack(Material.CHEST);
        ItemMeta chestMeta = chest.getItemMeta();
        assert chestMeta != null;
        chestMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aCosmetic Menu &7(Right-click)"));
        chestMeta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', "&7Right-click to open the Cosmetic menu.")));
        chestMeta.addEnchant(Enchantment.DURABILITY, 0, false);
        chestMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        chest.setItemMeta(chestMeta);
        return chest;
    }
}


