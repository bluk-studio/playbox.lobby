package org.playbox.services;

import org.playbox.resourcepack.bits.menus.gamemenu.ParkourTagMenuTexture;
import org.playbox.services.games.Game;
import org.playbox.services.games.GameMenuConfiguration;

import java.util.ArrayList;
import java.util.List;

public class GamesService {
    public static List<Game> GAMES = GamesService.fetchGames();

    public static List<Game> fetchGames() {
        // todo
        // fetch games from external source?
        return new ArrayList<>(
                List.of(
                    // ParkourTag
                    new Game.Builder()
                            .withName("Parkour Tag")
                            .withMenuConfiguration(
                                    new GameMenuConfiguration.Builder()
                                            .withTexture(ParkourTagMenuTexture.class)
                                            .build()
                            )
                            .build()
                )
        );
    };

    public static void refetchGames() {
        GAMES = GamesService.fetchGames();
    };
}
