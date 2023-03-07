package org.playbox.resourcepack.bits.hotbar;

import lombok.Getter;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.playbox.utils.ResourcePackUtils;
import org.playbox.utils.resourcepack.FontTexture;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

public class PlayButton extends FontTexture {
    private static final Character character = FontTexture.getNextAvailableSymbol();

    public PlayButton() {
        this.withTexture(
                Texture.builder()
                        .key(Key.key("playbox", "gui/hotbar/play_button/active"))
                        .data(ResourcePackUtils.getResourceWritable("textures/gui/hotbar/play_button/active.png"))
                        .build()
        );

        this.withProvider(
                FontProvider.bitMap()
                        .characters(String.valueOf(character))
                        .ascent(-35)
                        .height(30)
        );
    };

    public static Component asTextComponent() {
        return asTextComponentWrapper(character);
    };
}
