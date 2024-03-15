package de.artus.basePlugin.devUtils;

import de.artus.basePlugin.BasePlugin;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DevUtils {

    @Getter
    private final BasePlugin plugin;


    public void enableAutoReloadPlugin() {
        AutoReloadPlugin.startWatchService(getPlugin());
    }
}
