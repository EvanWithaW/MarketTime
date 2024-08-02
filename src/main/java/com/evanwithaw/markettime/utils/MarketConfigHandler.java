package com.evanwithaw.markettime.utils;

import com.evanwithaw.markettime.MarketTime;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static org.bukkit.Bukkit.getServer;

public class MarketConfigHandler {
    private FileConfiguration config;
    private File configFile;
    private MarketTime plugin;

    public MarketConfigHandler(MarketTime plugin) throws IOException {
        this.plugin = plugin;
        loadConfig();
    }

    private void loadConfig() throws IOException {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            plugin.saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Location getMarketLocation() {
        String[] locationParts = config.getString("market.location").split(",");
        return new Location(getServer().getWorld(locationParts[0]), Double.parseDouble(locationParts[1]), Double.parseDouble(locationParts[2]), Double.parseDouble(locationParts[3]));
    }

    public void setMarketLocation(Location location) {
        config.set("market.location", location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ());
        saveConfig();
    }

    public int getMarketOpenTime() {
        return config.getInt("market.openTime");
    }

    public void setMarketOpenTime(int openTime) {
        config.set("market.openTime", openTime);
        saveConfig();
    }

    public int getMarketRadius() {
        return config.getInt("market.radius");
    }

    public int getMarketCloseTime() {
        return config.getInt("market.closeTime");
    }

    public void setMarketCloseTime(int closeTime) {
        config.set("market.closeTime", closeTime);
        saveConfig();
    }


    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}