package org.playbox.utils.resourcepack;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.playbox.resourcepack.bits.Fonts;
import team.unnamed.creative.font.BitMapFontProvider;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

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
        return asTextComponentBuilderWrapper(character)
                .build();
    };

    public static TextComponent.Builder asTextComponentBuilderWrapper(Character character) {
        return Component.text()
                .content(String.valueOf(character))
                .font(Fonts.DEFAULT.key());
    };

    public static Component asTextComponent() { return Component.empty(); };
}
