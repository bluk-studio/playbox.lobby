package org.playbox.instances.limbo.managers;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.timer.ExecutionType;
import net.minestom.server.timer.Scheduler;
import net.minestom.server.timer.Task;
import net.minestom.server.timer.TaskSchedule;
import org.playbox.instances.Limbo;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RepeatableMessageManager {
    public static Map<UUID, ManagedPlayer> players = new HashMap<>();

    public @Nullable static Component getPlayersCurrentMessage(Player player) {
          if (players.get(player.getUuid()) == null) return null;
          ManagedPlayer managedPlayer = players.get(player.getUuid());

          return managedPlayer.getCurrentMessage();
    };

    public static void setMessage(Player player, Component message, @Nullable Component returnToMessage) {
        // Checking if this player exists in our manager
        if (players.get(player.getUuid()) == null) {
            players.put(player.getUuid(), new ManagedPlayer(player));
        };

        ManagedPlayer managedPlayer = players.get(player.getUuid());

        // Updating ManagedPlayer config
        if (returnToMessage != null) {
            managedPlayer.setNextMessage(returnToMessage);
        };

        managedPlayer.setCurrentMessage(message);

        // Starting our schedule task
        Scheduler scheduler = MinecraftServer.getSchedulerManager();

        // Cancelling our previous task
        if (managedPlayer.getTask() != null) {
            managedPlayer.getTask().cancel();
        };

        Task task = scheduler.scheduleTask(() -> {
            handler(player);
        }, TaskSchedule.immediate(), TaskSchedule.seconds(5), ExecutionType.ASYNC);

        managedPlayer.setTask(task);
    };

    public static void handler(Player player) {
        // Checking if our manager has this player
        if (!players.containsKey(player.getUuid())) {
            return;
        };

        ManagedPlayer managedPlayer = players.get(player.getUuid());

        if (player.getInstance() != Limbo.INSTANCE) {
            if (managedPlayer.getTask() != null) managedPlayer.getTask().cancel();
            return;
        };

        // Getting current message from our manager
        if (managedPlayer.getCurrentMessage() != null) {
            Component message = players.get(player.getUuid()).getCurrentMessage();

            // Clearing player's chat by spamming empty string...
            for (int i = 0; i < 25; i++) {
                player.sendMessage(Component.space());
            };

            assert message != null;
            player.sendMessage(message);
        };

        // Checking if we have our next message
        if (managedPlayer.getNextMessage() != null) {
            managedPlayer.setCurrentMessage(managedPlayer.getNextMessage());
            managedPlayer.setNextMessage(null);
        };
    };

    public static class ManagedPlayer {
        @Getter()
        private final Player player;

        @Getter()
        @Setter()
        private @Nullable Component currentMessage;

        @Getter()
        @Setter()
        private @Nullable Component nextMessage;

        @Getter()
        @Setter()
        private @Nullable Task task;

        public ManagedPlayer(Player player) {
            this.player = player;
        };
    }
}
