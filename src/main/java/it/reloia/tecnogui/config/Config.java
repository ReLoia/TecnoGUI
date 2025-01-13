package it.reloia.tecnogui.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.impl.serializer.GsonConfigSerializer;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Color.class, new GsonConfigSerializer.ColorTypeAdapter())
            .create();
    private static final File CONFIG_FILE = new File("config/tecnogui.json");
    
    private boolean shouldHideVoteAds = false;
    public boolean isHideVoteAds() {
        return shouldHideVoteAds;
    }
    public void setHideVoteAds(boolean hideBossBarName) {
        this.shouldHideVoteAds = hideBossBarName;
    }

    private boolean shouldHideEnteredPlotMsg = false;
    public boolean isHideEnteredPlotMsg() {
        return shouldHideEnteredPlotMsg;
    }
    public void setHideEnteredPlotMsg(boolean hideEnteredPlotMsg) {
        this.shouldHideEnteredPlotMsg = hideEnteredPlotMsg;
    }

    private boolean shouldHighlightExpiredFood = true;
    public boolean isHighlightExpiredFood() {
        return shouldHighlightExpiredFood;
    }
    public void setHighlightExpiredFood(boolean highlightExpiredFood) {
        this.shouldHighlightExpiredFood = highlightExpiredFood;
    }

    private boolean shouldHideScoreboard = true;
    public boolean isHideScoreboard() {
        return shouldHideScoreboard;
    }
    public void setHideScoreboard(boolean hideScoreboard) {
        this.shouldHideScoreboard = hideScoreboard;
    }
    
    private Color highlightExpiredFoodColor = new Color(0x8090EE90);
    public Color getHighlightExpiredFoodColor() {
        return highlightExpiredFoodColor;
    }
    public void setHighlightExpiredFoodColor(@NotNull Color color) {
        this.highlightExpiredFoodColor = color;
    }

    // TODO: add toggle for custom bars, 
    //  saturation in custom bars, 
    //  inventory buttons,

    public void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config load() {
        if (!CONFIG_FILE.exists()) {
            return new Config();
        }

        try {
            return GSON.fromJson(java.nio.file.Files.readString(CONFIG_FILE.toPath()), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Config();
        }
    }
}
