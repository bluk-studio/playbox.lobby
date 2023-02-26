package org.playbox.instances.limbo.managers.repeatablemanager;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.timer.Task;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;

public class ManagedPlayer {
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
