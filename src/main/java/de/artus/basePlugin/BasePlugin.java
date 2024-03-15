package de.artus.basePlugin;

import de.artus.basePlugin.commands.PluginCommand;
import de.artus.basePlugin.devUtils.DevUtils;
import de.artus.basePlugin.logging.ConsoleErrorManager;
import de.artus.basePlugin.logging.LogColor;
import de.artus.basePlugin.logging.LogSectionProgress;
import de.artus.basePlugin.logging.Logger;
import de.artus.basePlugin.text.Translations;
import io.leego.banana.BananaUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;


public class BasePlugin extends JavaPlugin {


    @Getter @Setter(AccessLevel.PRIVATE)
    private static BasePlugin instance;

    @Getter(onMethod = @__(@Override)) @Setter
    private PluginLogger logger = new Logger(this);

    @Getter @Setter
    private LogColor primaryLogColor = LogColor.PURPLE;

    @Getter @Setter (AccessLevel.PRIVATE)
    private ConsoleErrorManager consoleErrorManager;

    @Getter @Setter (AccessLevel.PRIVATE)
    private Translations translations;



    @Getter
    private Map<String, Listener> listeners = new HashMap<>();
    @Getter
    private Map<String, CommandExecutor> commands = new HashMap<>();


    @Override
    public void onLoad() {
        setInstance(this);
    }

    @Override
    public void onEnable() {
        // - - - - - - -  DRAW BIG PLUGIN NAME TEXT IN ASCII - - - - - - - - - - - - - - - - - - - - - -
        String pluginName = getName();
        String asciiText = BananaUtils.bananaify(pluginName);
        int asciiTextMaxLineLength = Collections.max(Arrays.stream(asciiText.split("\n")).map(String::length).toList());
        asciiText = "\n" + "-".repeat(asciiTextMaxLineLength) + "\n" + asciiText + "\n\n";

        String subTitle = "Made with <3 by " + String.join(", ", getDescription().getAuthors());
        int subTitleLength = asciiTextMaxLineLength / 2 - subTitle.length() / 2;
        String subSubTitle = "(%s)".formatted(getDescription().getWebsite());
        int subSubTitleLength = asciiTextMaxLineLength / 2 - subSubTitle.length() / 2;


        asciiText += " ".repeat(subTitleLength) + subTitle + " ".repeat(subTitleLength) + "\n";
        asciiText += " ".repeat(subSubTitleLength) + subSubTitle + " ".repeat(subSubTitleLength) + "\n";
        asciiText += "-".repeat(asciiTextMaxLineLength);

        for (String line : asciiText.split("\n")) {
            getLogger().info(line);
        }
        // ---------------------------------------------------------------------------------------------



        // - - - - - - - - - - CONSOLE ERROR HANDLER - - - - - - - - - - -
        LogSectionProgress errorManagerLoading = new LogSectionProgress("Loading error handler", getInstance()).start();
        setConsoleErrorManager(new ConsoleErrorManager(getInstance()));
        errorManagerLoading.end();
        // -----------------------------------------------------------------



        // - - - - - - - - - - TRANSLATIONS - - - - - - - - - - -
        LogSectionProgress translationLoading = new LogSectionProgress("Loading translation manager", getInstance()).start();
        setTranslations(new Translations(getInstance()));
        translationLoading.end();
        // -----------------------------------------------------------------




        // - - - - - - - - - - COMMANDS - - - - - - - - - - -
        LogSectionProgress commandLoading = new LogSectionProgress("Loading commands...", getInstance()).start();
        for (Map.Entry<String, CommandExecutor> commandEntry : getCommands().entrySet()) {
            commandLoading.addLine(commandEntry.getKey());
            getCommand(commandEntry.getKey()).setExecutor(commandEntry.getValue());
        }
        commandLoading.end();
        // --------------------------------------------------------

        // - - - - - - - - - - LISTENERS - - - - - - - - - - -
        LogSectionProgress listenerLoading = new LogSectionProgress("Loading listeners...", getInstance()).start();
        PluginManager pluginManager = Bukkit.getPluginManager();
        for (Map.Entry<String, Listener> listenerEntry : getListeners().entrySet()) {
            listenerLoading.addLine(listenerEntry.getKey());
            pluginManager.registerEvents(listenerEntry.getValue(), getInstance());
        }
        listenerLoading.end();
        // --------------------------------------------------------



    }





    public void addEventListener(Listener listener) {
        getListeners().put(listener.getClass().getSimpleName(), listener);
    }
    public void addEventListener(String name, Listener listener) {
        getListeners().put(name, listener);
    }

    public void addCommand(String name, CommandExecutor executor) {
        getCommands().put(name, executor);
    }
    public void addCommand(String name, PluginCommand pluginCommandExecutor) {
        pluginCommandExecutor.load(getInstance());
        getCommands().put(name, pluginCommandExecutor);
    }



    public DevUtils enableDevMode() {
        return new DevUtils(getInstance());
    }


    public File getPluginJarFile() {
        return getFile();
    }
    public void reloadPlugin() {
        getLogger().info(getPrimaryLogColor() + "Reloading Plugin, because AutoReloadPlugin is enabled with DevMode!");
        Bukkit.dispatchCommand(getServer().getConsoleSender(), "reload confirm");
    }
}