package com.gz.game_zone.service;

import com.gz.game_zone.dto.GamesDto;
import com.gz.game_zone.entity.Game;

import java.time.LocalDate;
import java.util.List;

public interface GameService {

    Game addGame(GamesDto gamesDto);
    Game updateGame(String name, GamesDto gameDto);
    void deleteGame(String name);

    List<Game> getFilteredGames(String name, String genre, LocalDate releasedDate, Long copiesSold, Float rating, Boolean isGameOfTheYear, String gameStudio, Long revenue);
}
