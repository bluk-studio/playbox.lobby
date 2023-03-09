package org.playbox.resourcepack;

import org.playbox.services.ResourcesDownloader;
import org.playbox.utils.ResourcePackUtils;
import org.playbox.utils.ResourceUtils;
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

        // Trying to download up-to-date resources from assets.k8s.odzi.dog
        try {
            ResourcesDownloader.fromBucket("playbox-resourcepack-lobby", "textures/");
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }

        // Initializing Lobby Resourcepack's bits
        ResourcePackUtils.initializeBits("org.playbox.resourcepack.bits", tree);
    });
}
