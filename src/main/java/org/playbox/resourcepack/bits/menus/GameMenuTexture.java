package org.playbox.resourcepack.bits.menus;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.playbox.utils.ResourcePackUtils;
import org.playbox.utils.resourcepack.FontTexture;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

public class GameMenuTexture extends FontTexture {
    public static Character character = FontTexture.getNextAvailableSymbol();

    public GameMenuTexture() {
        withTexture(
                Texture.builder()
                        .key(Key.key("playbox", "gui/menus/games/container"))
                        .data(ResourcePackUtils.getResourceWritable("textures/gui/menus/games/container.png"))
                        .build()
        );

        withProvider(
                FontProvider.bitMap()
                        .characters(String.valueOf(character))
                        .ascent(10)
                        .height(256)
        );
    };

    public static Component asTextComponent() {
        return asTextComponentWrapper(character);
    };

    public static TextComponent.Builder asTextComponentBuilder() {
        return asTextComponentBuilderWrapper(character);
    };
}
