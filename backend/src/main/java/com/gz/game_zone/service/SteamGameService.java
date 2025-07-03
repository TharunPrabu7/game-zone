package com.gz.game_zone.service;

import com.gz.game_zone.entity.SteamGame;

import java.time.LocalDate;
import java.util.List;

public interface SteamGameService {
    List<SteamGame> getFilteredGames(String name, LocalDate releasedDate, Float metaCritic);

    List<SteamGame> getAll(int pageNo, int pageSize);

    Object getGameByName(String name);
}
