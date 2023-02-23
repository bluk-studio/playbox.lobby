package org.playbox.lobby.resourcepack;

import org.playbox.lobby.utils.ResourcePackUtils;
import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.metadata.Metadata;
import team.unnamed.creative.metadata.PackMeta;

public class Packs {
    public static final ResourcePack LOBBY = ResourcePack.build(tree -> {
        // Metadata
        tree.write(
                Metadata.builder()
                        .add(PackMeta.of(6, "§3│ §b§lplaybox   \\n§3│ §r§7Server Resourcepack"))
                        .build()
        );

        // Initializing Lobby Resourcepack's bits
        ResourcePackUtils.initializeBits("org.playbox.lobby.resourcepack.bits", tree);
    });
}
