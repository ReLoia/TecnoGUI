package it.reloia.tecnogui.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/tecnogui.json");
    
    private boolean hideBossBarName = false;

    public boolean isHideVoteAds() {
        return hideBossBarName;
    }

    public void setHideVoteAds(boolean hideBossBarName) {
        this.hideBossBarName = hideBossBarName;
    }

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
