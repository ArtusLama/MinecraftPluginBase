package de.artus.basePlugin.logging;


import de.artus.basePlugin.BasePlugin;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLogger;

import java.util.logging.LogRecord;

public class Logger extends PluginLogger {

    @Getter
    private final BasePlugin plugin;
    public Logger(BasePlugin context) {
        super(context);
        plugin = context;
    }

    @Override
    public void log(LogRecord logRecord) {
        logRecord.setMessage(transformMessageWithUnicodes(getPlugin().getPrimaryLogColor() + logRecord.getMessage()) + LogColor.RESET);
        super.log(logRecord);
    }

    public String transformMessageWithUnicodes(String string) {
        for (LogUnicodeReplacements value : LogUnicodeReplacements.values()) {
            string = string.replace(value.getFrom(), value.getReplacement());
        }
        return string;
    }

}
