package org.playbox.managers.player;

import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import org.playbox.resourcepack.bits.badges.AdminBadge;
import org.playbox.resourcepack.bits.badges.ModeratorBadge;
import org.playbox.resourcepack.bits.badges.PlayerBadge;

public enum PlayerRole {
    PLAYER,
    MODERATOR,
    ADMIN;

    public static PlayerRole fromString(String role) {
        return switch (role) {
            case "admin" -> PlayerRole.ADMIN;
            case "moderator" -> PlayerRole.MODERATOR;
            default -> PlayerRole.PLAYER;
        };
    };

    public Component getChatBadge() {
        return switch (this) {
            case PLAYER -> PlayerBadge.asTextComponent();
            case MODERATOR -> ModeratorBadge.asTextComponent();
            case ADMIN -> AdminBadge.asTextComponent();
        };
    };
}
