package de.artus.basePlugin.logging;

import de.artus.basePlugin.BasePlugin;
import de.artus.basePlugin.text.DefaultMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ConsoleErrorManager {

    @Getter
    private BasePlugin plugin;

    public void raiseError(DefaultMessages message) {
        getPlugin().getLogger().severe(getPlugin().getTranslations().getFromKey(message.getKey()));
    }
}
