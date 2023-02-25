package org.playbox.utils;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import org.playbox.Server;
import org.reflections.Reflections;

import java.util.Set;

public class CommandUtils {
    public static void initializeCommands() {
        Reflections reflections = new Reflections("org.playbox.lobby");
        Set<Class<? extends Command>> commands = reflections.getSubTypesOf(Command.class);

        commands.forEach(commandClass -> {
            Command command;

            try {
                command = commandClass.getDeclaredConstructor().newInstance();
                MinecraftServer.getCommandManager().register(command);

                Server.LOGGER.info(String.format("Initialized command %s", commandClass.getName()));
            } catch (Throwable error) {
                Server.LOGGER.warn(String.format("Could not initialize command %s", commandClass.getName()));
            };
        });
    };
}
