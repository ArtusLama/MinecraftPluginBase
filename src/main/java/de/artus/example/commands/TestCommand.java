package de.artus.example.commands;


import de.artus.basePlugin.commands.PlayerPluginCommand;
import org.bukkit.command.Command;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;


public class TestCommand extends PlayerPluginCommand {

    @Override
    public boolean onPlayerCommand(Player player, Command command, String s, String[] args) {
        for (int i=0; i<Integer.parseInt(args[0]); i++) {
            player.getWorld().spawnEntity(player.getLocation(), EntityType.valueOf(args[1]));
        }
        return true;
    }

}
