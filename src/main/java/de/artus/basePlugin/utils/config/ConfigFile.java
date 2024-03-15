package de.artus.basePlugin.utils.config;

import de.artus.basePlugin.BasePlugin;
import de.artus.basePlugin.text.DefaultMessages;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;


public abstract class ConfigFile {

    @Getter
    private FileConfiguration config;

    @Getter (AccessLevel.PRIVATE)
    private BasePlugin plugin;
    @Getter (AccessLevel.PRIVATE)
    private File configFile;
    @Getter (AccessLevel.PRIVATE)
    private String configFileName;


    public void setupConfig(String configFileName, BasePlugin plugin) {
        this.plugin = plugin;
        this.configFileName = configFileName;
        configFile = new File(getPlugin().getDataFolder(), getConfigFileName() + ".yml");
        config = YamlConfiguration.loadConfiguration(configFile);

        if (!configFile.exists()) {
            if (getPlugin().getResource(getConfigFileName() + ".yml") == null) {
                try {
                    configFile.getParentFile().mkdirs();
                    configFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                getPlugin().saveResource(getConfigFileName() + ".yml", false);
            }
        }
    }

    public void saveConfig() {
        try { config.save(configFile); }
        catch (IOException e) {
            getPlugin().getConsoleErrorManager().raiseError(DefaultMessages.CONFIG_SAVE_ERROR);
        }
    }



}
