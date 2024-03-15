package de.artus.basePlugin.commands;

import de.artus.basePlugin.text.DefaultMessages;
import org.bukkit.command.Command;
import org.bukkit.command.ConsoleCommandSender;

public abstract class PlayerPluginCommand extends PluginCommand {



    @Override
    public boolean onConsoleCommand(ConsoleCommandSender console, Command command, String s, String[] args) {
        getPlugin().getConsoleErrorManager().raiseError(DefaultMessages.COMMAND_ONLY_FOR_PLAYERS);
        return false;
    }
}
