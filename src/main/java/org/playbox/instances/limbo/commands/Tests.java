package org.playbox.instances.limbo.commands;

import net.kyori.adventure.text.Component;
import net.minestom.server.command.builder.Command;
import org.playbox.resourcepack.bits.Font;
import org.playbox.resourcepack.bits.hotbar.AccountButton;

public class Tests extends Command {
    public Tests() {
        super("test");

        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("Hello there!");
            sender.sendMessage(
                    AccountButton.asTextComponent()
            );
        });
    }
}
