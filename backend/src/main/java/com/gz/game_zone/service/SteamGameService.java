package com.gz.game_zone.service;

import com.gz.game_zone.dto.GameDto;
import com.gz.game_zone.dto.GameSummaryDto;
import com.gz.game_zone.dto.SteamGameDto;
import com.gz.game_zone.dto.SteamGameResponse;
import com.gz.game_zone.entity.SteamGame;

import java.time.LocalDate;
import java.util.List;

public interface SteamGameService {
    List<SteamGame> getFilteredGames(String name, LocalDate releasedDate, Float metaCritic);

    List<SteamGameDto> getAll(int pageNo, int pageSize);
    Object getGameByName(String name);

    SteamGameResponse getAllGame(int pageNo, int pageSize);

    GameDto getGame(Integer appid);
    GameDto getGameByGenre(String genre);
    SteamGameDto getGameById(int id);

    // Add, Update and Delete a game
    SteamGameDto createGame(SteamGameDto steamGameDto);
    SteamGameDto updateGame(SteamGameDto steamGameDto, int id);
    void deleteGameById(int id);

    // Filters by tag, genre, developers and publishers
    List<GameSummaryDto> getByGenre(String genre);
    List<GameSummaryDto> getByTag(String tag);
    List<GameSummaryDto> getByDeveloper(String developer);
    List<GameSummaryDto> getByPublisher(String publisher);
}
