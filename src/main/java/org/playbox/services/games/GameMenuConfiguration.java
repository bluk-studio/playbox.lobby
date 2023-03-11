package org.playbox.services.games;

import lombok.Getter;
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
        THREE_TO_TWO,
        THREE_TO_THREE
    }

    public static class Builder {
        private Class<? extends FontTexture> texture;
        private ButtonDimension buttonDimension = ButtonDimension.THREE_TO_TWO;

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
