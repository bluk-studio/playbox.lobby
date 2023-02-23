package org.playbox.lobby.resourcepack.bits;

import net.kyori.adventure.key.Key;
import org.playbox.lobby.Server;
import org.playbox.lobby.utils.ResourcePackBit;
import org.playbox.lobby.utils.ResourcePackUtils;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.file.FileTree;
import team.unnamed.creative.texture.Texture;

import java.io.File;
import java.net.URISyntaxException;

public class TextureOverwrites extends ResourcePackBit {
    public void apply(FileTree tree) throws URISyntaxException {
        tree.write(
                Texture.builder()
                        .key(Key.key("minecraft", "gui/icons"))
                        .data(ResourcePackUtils.getResourceWritable("textures/gui/rewrites/icons.png"))
                        .build()
        );

        tree.write(
                Texture.builder()
                        .key(Key.key("minecraft", "gui/widgets"))
                        .data(ResourcePackUtils.getResourceWritable("textures/gui/rewrites/widgets.png"))
                        .build()
        );
    };
}
