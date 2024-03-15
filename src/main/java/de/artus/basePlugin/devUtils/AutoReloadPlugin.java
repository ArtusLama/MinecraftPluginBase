package de.artus.basePlugin.devUtils;

import de.artus.basePlugin.BasePlugin;
import de.artus.basePlugin.logging.Logger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class AutoReloadPlugin {

    @Getter @Setter(AccessLevel.PRIVATE)
    private static boolean isActive = false;

    public static void startWatchService(BasePlugin plugin) {
        try {
            File pluginJar = getPluginJarPath(plugin);
            WatchService watchService = FileSystems.getDefault().newWatchService();
            pluginJar.toPath().getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            setActive(true);
            Thread watcherThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted() && isActive()) {
                    try {
                        WatchKey key = watchService.take();
                        for (WatchEvent<?> event : key.pollEvents()) {
                            Path modifiedFile = (Path) event.context();

                            if (pluginJar.toPath().getFileName().equals(modifiedFile.getFileName())) { //TODO: NOT WORKING :(
                                setActive(false);
                                Bukkit.getScheduler().runTask(plugin, plugin::reloadPlugin);
                            }
                        }
                        key.reset();
                    } catch (Exception e) {
                        setActive(false);
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                        plugin.getLogger().severe("[AutoReloadPlugin] Error while watching plugin jar file!");
                    }
                }
            });
            watcherThread.setDaemon(true);
            watcherThread.start();

        } catch (Exception e) {
            setActive(false);
            plugin.getLogger().severe("[AutoReloadPlugin] Error while watching plugin jar file!");
        }

    }


    public static File getPluginJarPath(BasePlugin plugin) {
        return plugin.getPluginJarFile();
    }


}
