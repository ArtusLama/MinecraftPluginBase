package de.artus.basePlugin.text;

import de.artus.basePlugin.BasePlugin;
import de.artus.basePlugin.utils.config.ConfigFile;

public class Translations extends ConfigFile {


    public Translations(BasePlugin plugin) {
        setupConfig("pluginMessages", plugin);
    }

    public String getFromKey(String key) {
        return getConfig().getString(getSelectedLanguage() + "." + key, "NO_TRANSLATION_FOUND");
    }

    public String getSelectedLanguage() {
        return getConfig().getString("selected_language", "en");
    }

}
