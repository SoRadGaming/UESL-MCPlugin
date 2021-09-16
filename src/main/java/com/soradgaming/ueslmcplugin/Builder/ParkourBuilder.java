package com.soradgaming.ueslmcplugin.Builder;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import org.bukkit.configuration.ConfigurationSection;

public class ParkourBuilder {

    private ParkourBuilder name;
    private String parkour_name;
    private String data_name;
    private String region_finish_name;
    private String world_name;
    private int uesl_coin_prize;
    private int money_prize;

    /**
     * The Builder to create a parkour.
     * @param name The name of the parkour (usually lowercase)
     */
    public ParkourBuilder(ParkourBuilder name) {
        this.setName(name);
        setParkourName("");
        setDataName("");
        setRegionName("");
        setWorldName("");
        setUESLPrize(0);
        setMoneyPrize(0);
    }

    protected ParkourBuilder getName() {
        return name;
    }

    //Set the name of the Channel
    public ParkourBuilder setName(ParkourBuilder name) {
        this.name = name;
        return this;
    }

    //Return Parkour Name
    protected String getParkourName() {
        return parkour_name;
    }

    //Set Parkour Name
    public ParkourBuilder setParkourName(String parkour_name) {
        this.parkour_name = parkour_name;
        return this;
    }

    //Return data name
    protected String getDataName() {
        return data_name;
    }

    //Set data name
    public ParkourBuilder setDataName(String data_name) {
        this.data_name = data_name;
        return this;
    }

    //Return Region Name Linked to Finish
    protected String getRegionName() {
        return region_finish_name;
    }

    //Set Region Linked to Finish
    public ParkourBuilder setRegionName(String region_finish_name) {
        this.region_finish_name = region_finish_name;
        return this;
    }

    //Return Money Prize
    public int getMoneyPrize() {
        return money_prize;
    }

    //Set Money Prize
    public ParkourBuilder setMoneyPrize(int money_prize) {
        this.money_prize = money_prize;
        return this;
    }

    //Return Coin Prize
    public int getUESLPrize() {
        return uesl_coin_prize;
    }

    //Set Coin Prize
    public ParkourBuilder setUESLPrize(int uesl_coin_prize) {
        this.uesl_coin_prize = uesl_coin_prize;
        return this;
    }

    //Return World Linked to Parkour
    public String getWorld_name() {
        return world_name;
    }

    //Set World Linked to Parkour
    public ParkourBuilder setWorldName(String world_name) {
        this.world_name = world_name;
        return this;
    }

    /**
     * Build the Parkour Data
     * @return The Data. Note: If the Parkour name is already used it will return the existing one.
     */
    public ParkourBuilder build() {
        if(UESLMCPlugin.plugin.channel.contains(String.valueOf(this.name))) {
            ConfigurationSection cs = UESLMCPlugin.plugin.channel.getConfigurationSection(String.valueOf(this.name));
            assert cs != null;
            this.setParkourName(cs.getString("parkour_name"));
            this.setDataName(cs.getString("data_name"));
            this.setRegionName(cs.getString("region_finish_name"));
            this.setWorldName(cs.getString("world_name"));
            this.setUESLPrize(cs.getInt("uesl_coin_prize"));
            this.setMoneyPrize(cs.getInt("money_prize"));
        }
        return new ParkourBuilder(this);
    }
}

