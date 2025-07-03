package com.gz.game_zone.service.impl;

import com.gz.game_zone.entity.SteamGame;
import com.gz.game_zone.repo.SteamGameRepository;
import com.gz.game_zone.service.SteamGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SteamGameServiceImpl implements SteamGameService {

    @Autowired
    private SteamGameRepository repo;


    @Override
    public List<SteamGame> getFilteredGames(String name, LocalDate releasedDate, Float metaCritic) {
        return repo.findAll().stream()
                .filter(g -> name == null ||
                        (g.getName() != null && g.getName().toLowerCase().contains(name.toLowerCase())))
                .filter(g -> releasedDate == null || releasedDate.equals(g.getReleasedDate()))
                .filter(g -> metaCritic == null || (g.getMetaCritic() != null && g.getMetaCritic() >= metaCritic))
                .toList();
    }

    @Override
    public List<SteamGame> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<SteamGame> games = repo.findAll(pageable);
        List<SteamGame> listOfSteamGame = games.getContent();
        return listOfSteamGame.stream().toList();
    }

    @Override
    public Object getGameByName(String name) {
        return name;
    }
}
