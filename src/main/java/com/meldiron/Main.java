package com.meldiron;

// 25x25, vyska hore +10, dole -7

import com.meldiron.commands.InfinityParkourCmd;
import com.meldiron.events.HungerEvent;
import com.meldiron.events.LeaveEvent;
import com.meldiron.events.MoveEvent;
import com.meldiron.guis.InfinityParkourGUI;
import com.meldiron.libs.FileManager;
import com.meldiron.libs.GUIManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private FileManager fm;
    private YamlConfiguration config;
    private YamlConfiguration langConfig;
    private YamlConfiguration scoreboard;
    private static Main instance;
    private GameManager gm;

    public static Main getInstance() {
        return Main.instance;
    }

    @Override
    public void onEnable() {
        this.instance = this;


        fm = new FileManager(this);
        fm.getConfig("config.yml").copyDefaults(true).save();
        this.config = fm.getConfig("config.yml").get();
        fm.getConfig("translations.yml").copyDefaults(true).save();
        this.langConfig = fm.getConfig("translations.yml").get();
        fm.getConfig("scoreboard.yml").copyDefaults(true).save();
        this.scoreboard = fm.getConfig("scoreboard.yml").get();

        this.getCommand("infinityparkour").setExecutor(new InfinityParkourCmd());
        getServer().getPluginManager().registerEvents(new GUIManager(), this);
        getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        getServer().getPluginManager().registerEvents(new HungerEvent(), this);
        getServer().getPluginManager().registerEvents(new LeaveEvent(), this);

        gm = new GameManager();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void reloadConfigs() {
        this.config = fm.reloadConfig("config.yml").get();
        this.langConfig = fm.reloadConfig("translations.yml").get();
        this.scoreboard = fm.reloadConfig("scoreboard.yml").get();

        InfinityParkourGUI.refresh();
    }

    public static String formatedMsg(String msg) {
        return Main.getInstance().getLangConfig().getString("chat.prefix") + msg;
    }

    public YamlConfiguration getConfig() {
        return this.config;
    }

    public YamlConfiguration getLangConfig() {
        return this.langConfig;
    }

    public GameManager getGm() {
        return gm;
    }

    public FileManager getFm() {
        return fm;
    }

    public YamlConfiguration getScoreboard() {
        return this.scoreboard;
    }
}