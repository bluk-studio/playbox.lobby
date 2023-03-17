package org.playbox.services.games;

import lombok.Getter;
import org.javatuples.Pair;
import org.playbox.utils.resourcepack.FontTexture;

public class GameMenuConfiguration {
    @Getter()
    private final Class<FontTexture> texture;

    @Getter()
    private final ButtonDimension buttonDimension;

    public GameMenuConfiguration(Class<FontTexture> texture, ButtonDimension buttonDimension) {
        this.texture = texture;
        this.buttonDimension = buttonDimension;
    }

    // todo
    // generalize and move to utils
    public static enum ButtonDimension {
        FOUR_TO_TWO,
        FOUR_TO_THREE;

        // (width, height)
        public Pair<Integer, Integer> getDimensions() {
            return switch (this) {
                case FOUR_TO_TWO -> new Pair<Integer, Integer>(4, 3);
                case FOUR_TO_THREE -> new Pair<Integer, Integer>(4, 2);
            };
        };

        public int getHeight() {
            return this.getDimensions().getValue0();
        };

        public int getWidth() {
            return this.getDimensions().getValue1();
        };
    }

    public static class Builder {
        private Class<? extends FontTexture> texture;
        private ButtonDimension buttonDimension = ButtonDimension.FOUR_TO_TWO;

        public Builder withTexture(Class<? extends FontTexture> texture) {
            this.texture = texture;
            return this;
        }

        public Builder withDimension(ButtonDimension dimension) {
            this.buttonDimension = dimension;
            return this;
        }

        public GameMenuConfiguration build() {
            return new GameMenuConfiguration(
                    (Class<FontTexture>) this.texture,
                    this.buttonDimension
            );
        }
    }
}
