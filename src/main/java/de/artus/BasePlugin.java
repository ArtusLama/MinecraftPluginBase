package de.artus;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public class BasePlugin extends JavaPlugin {


    @Getter @Setter
    private static JavaPlugin instance;

    @Override
    public void onLoad() {
        setInstance(this);
    }
}