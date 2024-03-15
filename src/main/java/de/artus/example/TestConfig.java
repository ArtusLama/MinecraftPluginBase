package de.artus.example;

import de.artus.basePlugin.BasePlugin;
import de.artus.basePlugin.utils.config.ConfigFile;

public class TestConfig extends ConfigFile {


    public TestConfig(BasePlugin plugin) {
        setupConfig("testConfig", plugin);
    }


}
