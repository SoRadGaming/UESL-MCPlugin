package com.soradgaming.ueslmcplugin.Builder;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ChannelBuilder {

    private ChannelBuilder name;
    private String channel_name;
    private boolean region_chat;
    private List<String> region_name;
    private boolean world_chat;
    private List<String> world_name;

    /**
     * The Builder to create a channel.
     * @param name The name of the channel (usually lowercase)
     */
    public ChannelBuilder(ChannelBuilder name) {
        this.setName(name);
        setChannelName("'" + name + "'");
        setRegionChat(false);
        setRegionName(new ArrayList<>());
        setWorldChat(true);
        setWorldName(new ArrayList<>());
    }

    protected ChannelBuilder getName() {
        return name;
    }

    //Set the name of the Channel
    public ChannelBuilder setName(ChannelBuilder name) {
        this.name = name;
        return this;
    }

    //Return Channel Name for UltraChat
    protected String getChannelName() {
        return channel_name;
    }

    //Set Channel Name for UltraChat
    public ChannelBuilder setChannelName(String channel_name) {
        this.channel_name = channel_name;
        return this;
    }

    //Return Region Chat Status
    protected boolean getRegionChat() {
        return region_chat;
    }

    //Set Channel Region Chat Status
    public ChannelBuilder setRegionChat(boolean region_chat) {
        this.region_chat = region_chat;
        return this;
    }

    //Return Region Name Linked to Channel
    protected List<String> getRegionName() {
        return region_name;
    }

    //Set Region Linked to Channel
    public ChannelBuilder setRegionName(List<String> region_name) {
        this.region_name = region_name;
        return this;
    }

    //Return Wold Chat Status
    public boolean getWorldChat() {
        return world_chat;
    }

    //Set World Chat Status
    public ChannelBuilder setWorldChat(Boolean world_chat) {
        this.world_chat = world_chat;
        return this;
    }

    //Return World Linked to Channel
    public List<String> getWorld_name() {
        return world_name;
    }

    //Set World Linked to Channel
    public ChannelBuilder setWorldName(List<String> world_name) {
        this.world_name = world_name;
        return this;
    }

    /**
     * Build the channel
     * @return The channel. Note: If the channel name is already used it will return the existing one.
     */
    public ChannelBuilder build() {
        if(UESLMCPlugin.plugin.channel.contains(String.valueOf(this.name))) {
            ConfigurationSection cs = UESLMCPlugin.plugin.channel.getConfigurationSection(String.valueOf(this.name));
            assert cs != null;
            this.setChannelName(cs.getString("channel_name"));
            this.setRegionChat(cs.getBoolean("region_chat"));
            this.setRegionName(cs.getStringList("region_name"));
            this.setWorldChat(cs.getBoolean("world_chat"));
            this.setWorldName(cs.getStringList("world_name"));
        }
        return new ChannelBuilder(this);
    }
}

