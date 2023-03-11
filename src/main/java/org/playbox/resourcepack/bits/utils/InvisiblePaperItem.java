package org.playbox.resourcepack.bits.utils;

import net.kyori.adventure.key.Key;
import org.playbox.utils.ResourcePackBit;
import org.playbox.utils.ResourcePackUtils;
import team.unnamed.creative.file.FileTree;
import team.unnamed.creative.model.Model;
import team.unnamed.creative.texture.Texture;

public class InvisiblePaperItem extends ResourcePackBit {
    public void apply(FileTree tree) {
        tree.write(
                Texture.builder()
                        .key(Key.key("minecraft", "item/paper"))
                        .data(ResourcePackUtils.getResourceWritable("textures/items/rewrites/paper.png"))
                        .build()
        );
    };
}
