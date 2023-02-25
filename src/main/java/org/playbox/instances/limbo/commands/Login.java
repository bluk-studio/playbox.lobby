package org.playbox.instances.limbo.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import org.playbox.instances.Lobby;
import org.playbox.instances.Limbo;

public class Login extends Command {
    public Login() {
        super("login");

        setDefaultExecutor((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            if (!this.isInLimbo(player)) return;

            sender.sendMessage("Usage: /login <password>");
        });

        var passwordArgument = ArgumentType.String("password");
        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            if (!this.isInLimbo(player)) return;

            final String password = context.get(passwordArgument);
            sender.sendMessage("You typed" + password);

            player.setInstance(Lobby.INSTANCE);
        }, passwordArgument);
    };

    private boolean isInLimbo(Player player) {
        return player.getInstance() == Limbo.INSTANCE;
    };
}
