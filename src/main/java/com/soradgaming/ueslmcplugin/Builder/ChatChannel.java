package com.soradgaming.ueslmcplugin.Builder;

import java.util.List;

public class ChatChannel {

    private ChannelBuilder name;
    private String channel_name;
    private boolean region_chat;
    private List<String> region_name;
    private boolean world_chat;
    private List<String> world_name;

    /**
     * For internal use only. Always use the channel builder.
     * @param cb The builder that was used to build the channel.
     */
    public ChatChannel(ChannelBuilder cb) {
        this.name = cb.getName();
        this.channel_name = cb.getChannelName();
        this.region_chat = cb.getRegionChat();
        this.region_name = cb.getRegionName();
        this.world_chat = cb.getWorldChat();
        this.world_name = cb.getWorld_name();
    }

    //Get Name of Channel
    public ChannelBuilder getName() {
        return name;
    }

    //Set the name of the Channel
    public ChatChannel setName(ChannelBuilder name) {
        this.name = name;
        return this;
    }

    //Return Channel Name for UltraChat
    public String getChannelName() {
        return channel_name;
    }

    //Set Channel Name for UltraChat
    public ChatChannel setChannelName(String channel_name) {
        this.channel_name = channel_name;
        return this;
    }

    //Return Region Chat Status
    public boolean getRegionChat() {
        return region_chat;
    }

    //Set Channel Region Chat Status
    public ChatChannel setRegionChat(boolean region_chat) {
        this.region_chat = region_chat;
        return this;
    }

    //Return Region Name Linked to Channel
    public List<String> getRegionName() {
        return region_name;
    }

    //Set Region Linked to Channel
    public ChatChannel setRegionName(List<String> region_name) {
        this.region_name = region_name;
        return this;
    }

    //Return Wold Chat Status
    public boolean getWorldChat() {
        return world_chat;
    }

    //Set World Chat Status
    public ChatChannel setWorldChat(Boolean world_chat) {
        this.world_chat = world_chat;
        return this;
    }

    //Return World Linked to Channel
    public List<String> getWorld_name() {
        return world_name;
    }

    //Set World Linked to Channel
    public ChatChannel setWorldName(List<String> world_name) {
        this.world_name = world_name;
        return this;
    }

    /*
    //Call to save Channel File Changes
    private void update() {
        UESLMCPlugin plugin = UESLMCPlugin.plugin;
        plugin.channel.set(this.getName() + ".channel_name", this.getChannelName());
        plugin.channel.set(this.getName() + ".region_chat", this.getRegionChat());
        plugin.channel.set(this.getName() + ".region_name", this.getRegionName());
        plugin.channel.set(this.getName() + ".world_chat", this.getWorldChat());
        plugin.channel.set(this.getName() + ".world_name", this.getWorld_name());
    }
    */

}
