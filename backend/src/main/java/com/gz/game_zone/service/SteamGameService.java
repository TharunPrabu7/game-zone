package com.gz.game_zone.service;

import com.gz.game_zone.dto.*;
import com.gz.game_zone.entity.SteamGame;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface SteamGameService {
    List<SteamGame> getFilteredGames(String name, LocalDate releasedDate, Float metaCritic);

    Object getGameByName(String name);

    SteamGameResponse getAllGame(int pageNo, int pageSize);

    // all details from name till publisher
    GameDto getGame(Integer appid);

    // app id, name, meta critic, released_date
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

    // Filters by tag, genre, developers and publishers with pagination
    PagedResponse<GameSummaryDto> getByGenreWithPagination(String genre, int pageNo, int pageSize);
    PagedResponse<GameSummaryDto> getByTagWithPagination(String tag, int pageNo, int pageSize);
    PagedResponse<GameSummaryDto> getByDeveloperWithPagination(String developer, int pageNo, int pageSize);
    PagedResponse<GameSummaryDto> getByPublisherWithPagination(String publisher, int pageNo, int pageSize);

    List<SteamGameDto> findGameWithSorting(String field);
}
