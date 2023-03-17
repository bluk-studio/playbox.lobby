package org.playbox.resourcepack.bits.fonts;

import net.kyori.adventure.key.Key;
import org.javatuples.Triplet;
import org.playbox.utils.ResourcePackBit;
import org.playbox.utils.ResourcePackUtils;
import team.unnamed.creative.file.FileTree;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

import java.util.HashMap;
import java.util.Map;

public class SpaceFontTextures extends ResourcePackBit {
    public void apply(FileTree tree) {
        // Aplying default split texture
        tree.write(SPLIT_TEXTURE);

        // Applying all these textures
    };

    public static Map<Integer, Triplet<Character, Texture, FontProvider>> MAP;

    public static Texture SPLIT_TEXTURE = Texture.builder()
            .key(Key.key("playbox", "utils/space_split"))
            .data(ResourcePackUtils.getResourceWritable("textures/utils/space_split.png"))
            .build();

    static {
        // todo
        // Generating TEXTURES map
        MAP = new HashMap<>();
    }
}
