package org.playbox.instances.limbo.commands;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import org.playbox.instances.Limbo;
import org.playbox.instances.Lobby;
import org.playbox.instances.limbo.consts.RegisterMessages;
import org.playbox.instances.limbo.managers.RepeatableMessageManager;
import org.playbox.managers.PlayerManager;
import org.playbox.services.AuthService;

import java.util.Objects;

public class Register extends Command {
    public Register() {
        super("register");

        setDefaultExecutor((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            if (!this.isInLimbo(player)) return;

            sender.sendMessage("Usage: /register [password] [passwordConfirm]");
        });

        var passwordArgument = ArgumentType.String("password");
        var passwordConfirmArgument = ArgumentType.String("passwordConfirm");
        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            if (!this.isInLimbo(player)) return;

            // Checking if this player is not registered
            if (PlayerManager.getByUUID(player.getUuid()).getIsRegistered()) {
                // Getting current player message, because it either could be LoginMessages.REPEATABLE_MESSAGE
                // or RegisterMessages.REPEATABLE_MESSAGE
                RepeatableMessageManager.setMessage(player, RegisterMessages.NOT_REGISTERED, RepeatableMessageManager.getPlayersCurrentMessage(player));
                return;
            };

            // Getting password and passwordConfirm arguments
            final String password = context.get(passwordArgument);
            final String passwordConfirm = context.get(passwordConfirmArgument);

            if (!Objects.equals(password, passwordConfirm)) {
                RepeatableMessageManager.setMessage(player, RegisterMessages.PASSWORDS_DO_NOT_MATCH, RegisterMessages.REPEATABLE_MESSAGE);
                return;
            };

            // Trying to register this player...
            RepeatableMessageManager.setMessage(player, RegisterMessages.PROCESSING, null);

            MinecraftServer.getSchedulerManager().scheduleNextTick(() -> {
                if (!AuthService.tryToRegisterPlayer(player, password)) {
                    RepeatableMessageManager.setMessage(player, RegisterMessages.ERROR, RegisterMessages.REPEATABLE_MESSAGE);
                    return;
                };

                player.setInstance(Lobby.INSTANCE, new Pos(0, 157, 0));
            });
        }, passwordArgument, passwordConfirmArgument);
    };

    private boolean isInLimbo(Player player) {
        return player.getInstance() == Limbo.INSTANCE;
    };
}
