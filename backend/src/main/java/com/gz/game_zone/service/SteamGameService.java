package com.gz.game_zone.service;

import com.gz.game_zone.dto.SteamGameDto;
import com.gz.game_zone.entity.SteamGame;

import java.time.LocalDate;
import java.util.List;

public interface SteamGameService {
    List<SteamGame> getFilteredGames(String name, LocalDate releasedDate, Float metaCritic);

    List<SteamGameDto> getAll(int pageNo, int pageSize);
    Object getGameByName(String name);

    List<SteamGameDto> getAllGame();
    SteamGameDto createGame(SteamGameDto steamGameDto);
    SteamGameDto getGameById(int id);
    SteamGameDto updateGame(SteamGameDto steamGameDto, int id);
    void deleteGameById(int id);

}
