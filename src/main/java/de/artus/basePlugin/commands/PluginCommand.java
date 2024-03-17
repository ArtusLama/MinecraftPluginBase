package de.artus.basePlugin.commands;

import de.artus.basePlugin.BasePlugin;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class PluginCommand implements CommandExecutor {

    @Getter @Setter(AccessLevel.PRIVATE)
    private BasePlugin plugin;

    public void load(BasePlugin plugin) {
        setPlugin(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        if (commandSender instanceof Player player) return onPlayerCommand(player, command, s, args);
        else if (commandSender instanceof ConsoleCommandSender console) return onConsoleCommand(console, command, s, args);
        return false;
    }

    public abstract boolean onPlayerCommand(Player player, Command command, String s, String[] args);
    public abstract boolean onConsoleCommand(ConsoleCommandSender console, Command command, String s, String[] args);

}
