package de.artus.basePlugin.commands;

import de.artus.basePlugin.text.DefaultMessages;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public abstract class ConsolePluginCommand extends PluginCommand {


    @Override
    public boolean onPlayerCommand(Player player, Command command, String s, String[] args) {
        getPlugin().getConsoleErrorManager().raiseError(DefaultMessages.COMMAND_ONLY_FOR_CONSOLE);
        return false;
    }
}
