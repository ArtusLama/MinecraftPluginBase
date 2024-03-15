package de.artus.basePlugin.logging;


import de.artus.basePlugin.BasePlugin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@AllArgsConstructor
public class LogSectionProgress {


    @Getter
    private final String sectionName;

    @Getter
    private final BasePlugin pluginClass;


    public LogSectionProgress start() {
       getPluginClass().getLogger().info(getPluginClass().getPrimaryLogColor() + "[%s]".formatted(getSectionName()));
       return this;
    }

    public void addLine(String line) {
        getPluginClass().getLogger().info(getPluginClass().getPrimaryLogColor() + "> %s".formatted(line));
    }

    public void end() {
        getPluginClass().getLogger().info(getPluginClass().getPrimaryLogColor() + "=> Done!");
    }


}
