package org.playbox.instances.limbo.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.timer.Scheduler;
import net.minestom.server.timer.TaskSchedule;
import org.playbox.instances.Lobby;
import org.playbox.instances.Limbo;
import org.playbox.instances.limbo.consts.LoginMessages;
import org.playbox.instances.limbo.managers.RepeatableMessageManager;
import org.playbox.managers.PlayerManager;
import org.playbox.services.AuthService;
import org.playbox.services.ProfileService;

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

            // Checking if this player is registered
            if (!PlayerManager.getByUUID(player.getUuid()).isRegistered) {
                RepeatableMessageManager.setMessage(player, LoginMessages.NOT_REGISTERED, LoginMessages.REPEATABLE_MESSAGE);
                return;
            };

            // Sending message that we're trying to authorize this player
            RepeatableMessageManager.setMessage(player, LoginMessages.PROCESSING, null);

            // Trying to authorize this player
            MinecraftServer.getSchedulerManager().scheduleNextTick(() -> {
                if (!AuthService.tryToAuthorizePlayer(player, password)) {
                    RepeatableMessageManager.setMessage(player, LoginMessages.ERROR, LoginMessages.REPEATABLE_MESSAGE);
                    return;
                };

                player.setInstance(Lobby.INSTANCE, new Pos(0, 157, 0));
            });
        }, passwordArgument);
    };

    private boolean isInLimbo(Player player) {
        return player.getInstance() == Limbo.INSTANCE;
    };
}
