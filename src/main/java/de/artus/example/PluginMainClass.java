package de.artus.example;

import de.artus.basePlugin.BasePlugin;
import de.artus.basePlugin.devUtils.DevUtils;
import de.artus.example.commands.TestCommand;
import de.artus.example.listeners.BlockBreakEvent;
import de.artus.example.listeners.ExampleEvent;
import de.artus.example.listeners.OtherExampleEvent;

public class PluginMainClass extends BasePlugin {

    @Override
    public void onEnable() {
        DevUtils devUtils = enableDevMode();
        devUtils.enableAutoReloadPlugin();


        // register Events before calling super method
        addEventListener(new BlockBreakEvent());
        addEventListener("Sehr krasses Event", new ExampleEvent());
        addEventListener(new OtherExampleEvent());

        addCommand("test123", new TestCommand());

        new TestConfig(getInstance());

        super.onEnable();
    }
}
