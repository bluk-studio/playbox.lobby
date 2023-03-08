package org.playbox.utils.resourcepack;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.playbox.Server;
import org.playbox.resourcepack.bits.Fonts;
import team.unnamed.creative.font.BitMapFontProvider;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class FontTexture {
    @Getter()
    @NotNull()
    private Texture texture;

    @Getter()
    @NotNull()
    private FontProvider provider;

    @Getter()
    private static Character character;

    public static Integer CURRENT_SYMBOL = 0;

    public static Character getNextAvailableSymbol() {
        // Getting (and then removing) first element of AVAILABLE_SYMBOLS list
        Character symbol = (char) (FontTexture.CURRENT_SYMBOL + 1);
        FontTexture.CURRENT_SYMBOL++;

        return symbol;
    };

    public void withTexture(Texture texture) {
        this.texture = texture;
    };

    public void withProvider(BitMapFontProvider.Builder providerBuilder) {
        providerBuilder.file(this.texture.key());

        this.provider = providerBuilder.build();
    };

    public static Component asTextComponentWrapper(Character character) {
        return Component.text()
                .content(String.valueOf(character))
                .font(Fonts.LOBBY.key())
                .build();
    };

    public static Component asTextComponent() { return Component.empty(); };
}
