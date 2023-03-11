package org.playbox.resourcepack.bits.menus.gamemenu;

import net.kyori.adventure.text.Component;
import org.playbox.utils.resourcepack.FontTexture;

public class ParkourTagMenuTexture extends FontTexture {
    public static Character character = FontTexture.getNextAvailableSymbol();

    public ParkourTagMenuTexture() {
        // todo
    };

    public static Component asTextComponent() {
        return asTextComponentWrapper(character);
    };
}
