package de.artus.basePlugin.text;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DefaultMessages {

    COMMAND_ONLY_FOR_PLAYERS("errors.commands.wrong_executor.players_only"),
    COMMAND_ONLY_FOR_CONSOLE("errors.commands.wrong_executor.console_only"),

    CONFIG_SAVE_ERROR("errors.config.save");

    @Getter
    private final String key;

}
