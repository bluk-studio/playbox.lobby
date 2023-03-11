package org.playbox.services.games;

import lombok.Getter;
import org.playbox.utils.resourcepack.FontTexture;

public class Game {
    @Getter()
    private final String name;

    @Getter()
    private final GameMenuConfiguration gameMenuConfiguration;

    public Game(String name, GameMenuConfiguration gameMenuConfiguration) {
        this.name = name;
        this.gameMenuConfiguration = gameMenuConfiguration;
    };

    public static class Builder {
        private String name;
        private GameMenuConfiguration configuration;

        public Builder withName(String name) {
            this.name = name;
            return this;
        };

        public Builder withMenuConfiguration(GameMenuConfiguration configuration) {
            this.configuration = configuration;
            return this;
        };

        public Game build() {
            return new Game(
                    this.name,
                    this.configuration
            );
        };
    };
}
