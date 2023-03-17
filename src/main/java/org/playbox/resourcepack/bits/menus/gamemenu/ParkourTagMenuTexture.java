package org.playbox.resourcepack.bits.menus.gamemenu;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.playbox.utils.ResourcePackUtils;
import org.playbox.utils.resourcepack.FontTexture;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

public class ParkourTagMenuTexture extends FontTexture {
    public static Character character = FontTexture.getNextAvailableSymbol();

    public ParkourTagMenuTexture() {
        withTexture(
                Texture.builder()
                        .key(Key.key("playbox", "gui/menus/games/parkour_tag_card"))
                        .data(ResourcePackUtils.getResourceWritable("textures/gui/menus/games/parkour_tag_card.png"))
                        .build()
        );

        withProvider(
                FontProvider.bitMap()
                        .characters(String.valueOf(character))
                        .ascent(0)
                        .height(36)
        );
    };

    public static Component asTextComponent() {
        return asTextComponentWrapper(character);
    };
}
