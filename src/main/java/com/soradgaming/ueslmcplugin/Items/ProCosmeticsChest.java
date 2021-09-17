package com.soradgaming.ueslmcplugin.Items;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import net.raidstone.wgevents.events.RegionsChangedEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class ProCosmeticsChest implements Listener {

    private final UESLMCPlugin plugin;

    //This Code should not be required and be used in the Procosmetic plugin
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
                }
            }
            catch (Exception var3_3) {
                plugin.getLogger().info("[UESL-MCPlugin] A open-chest error has been thrown. Please contact SoRadGaming if the problem persist.");
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

    /*
    //Prevent Dropping the Chest on Death
    @EventHandler
    public void onDeathDrop(PlayerDeathEvent event) {
        ItemStack chest = event.getDrops().get(8);
        if (chest.isSimilar(chest) && chest.hasItemMeta() && Objects.requireNonNull(chest.getItemMeta()).getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aCosmetic Menu &7(Right-click)"))) {
            chest.setData();
        }
    }
     */

    //Give Chest on Join
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (p.getWorld().getName().equals("Lobbyy") && plugin.getConfig().getBoolean("Procosmetic_Chest")) {
            ItemStack chest = this.Chest();
            if (!p.getInventory().contains(chest)) {
                p.getInventory().setHeldItemSlot(0);
                p.getInventory().setItem(8, chest);
                plugin.getLogger().info("[UESL-MCPlugin] " + p.getName() + " does not have a chest. Giving it now.");
            }
        }
    }
    //Chest Region
    @EventHandler
    public void OnRegionChange(RegionsChangedEvent event) {
        Player p = Bukkit.getPlayer(event.getUUID());
        ItemStack chest = this.Chest();
        Set<String> currentRegionsNames = event.getCurrentRegionsNames();
        Set<String> previousRegionsNames = event.getPreviousRegionsNames();
        assert p != null;
        String world = p.getWorld().toString();

        if (currentRegionsNames.contains("minigames") || world.equals("Lobbyy") && plugin.getConfig().getBoolean("Procosmetic_Chest")) {
            if (!p.getInventory().contains(this.Chest()) && !p.getInventory().getItemInOffHand().isSimilar(chest)) {
                plugin.getLogger().info("[UESL-MCPlugin] " + p.getName() + " does not have a chest. Giving it now.");
                p.getInventory().setHeldItemSlot(0);
                p.getInventory().setItem(8, this.Chest());
            }
        } else if (previousRegionsNames.contains("minigames") && world.equals("Lobbyy")) {
            if (p.getInventory().contains(this.Chest()) && !p.getInventory().getItemInOffHand().isSimilar(chest) && plugin.getConfig().getBoolean("Procosmetic_Chest")) {
                plugin.getLogger().info("[UESL-MCPlugin] " + p.getName() + " has entered a non-chest region. Removing chest or making it invalid now.");
                p.getInventory().setHeldItemSlot(0);
                p.getInventory().removeItem(chest);
            }
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


