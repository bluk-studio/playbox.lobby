package org.playbox.resourcepack.bits;

import net.kyori.adventure.key.Key;
import org.javatuples.Triplet;
import org.playbox.resourcepack.bits.fonts.LobbyFontTextures;
import org.playbox.resourcepack.bits.fonts.SpaceFontTextures;
import org.playbox.utils.ResourcePackBit;
import org.playbox.utils.resourcepack.FontTexture;
import team.unnamed.creative.file.FileTree;

public class Fonts extends ResourcePackBit {
    public void apply(FileTree tree) {
        // Applying our fonts
        tree.write(DEFAULT);
        tree.write(SPACE);
    };

    public static team.unnamed.creative.font.Font DEFAULT;

    public static team.unnamed.creative.font.Font SPACE;

    // Initializing default lobby font
    static {
        // Creating new font with this providers
        DEFAULT = team.unnamed.creative.font.Font.of(
                Key.key("playbox", "font_textures"),
                LobbyFontTextures.LIST.stream().map(FontTexture::getProvider).toList()
        );
    }

    // Initializing space font
    static {
        SPACE = team.unnamed.creative.font.Font.of(
                Key.key("playbox", "space"),
                SpaceFontTextures.MAP.values().stream().map(Triplet::getValue2).toList()
        );
    }
}
