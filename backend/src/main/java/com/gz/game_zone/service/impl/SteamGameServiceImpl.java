package com.gz.game_zone.service.impl;

import com.gz.game_zone.dto.SteamGameDto;
import com.gz.game_zone.entity.SteamGame;
import com.gz.game_zone.exceptions.GameNotFoundException;
import com.gz.game_zone.repo.SteamGameRepository;
import com.gz.game_zone.service.SteamGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SteamGameServiceImpl implements SteamGameService {

    @Autowired
    private SteamGameRepository steamGameRepository;

    @Override
    public List<SteamGame> getFilteredGames(String name, LocalDate releasedDate, Float metaCritic) {
        return steamGameRepository.findAll().stream()
                .filter(g -> name == null ||
                        (g.getName() != null && g.getName().toLowerCase().contains(name.toLowerCase())))
                .filter(g -> releasedDate == null || releasedDate.equals(g.getReleasedDate()))
                .filter(g -> metaCritic == null || (g.getMetaCritic() != null && g.getMetaCritic() >= metaCritic))
                .toList();
    }

    @Override
    public List<SteamGameDto> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<SteamGame> games = steamGameRepository.findAll(pageable);
        List<SteamGame> listOfSteamGame = games.getContent();
        return listOfSteamGame.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<SteamGameDto> getAllGame() {
        List<SteamGame> game = steamGameRepository.findAll();
        return game.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private SteamGameDto mapToDto(SteamGame steamGame) {
        SteamGameDto steamGameDto = new SteamGameDto();
        steamGameDto.setAppId(steamGame.getAppId());
        steamGameDto.setName(steamGame.getName());
        steamGameDto.setReleasedDate(steamGame.getReleasedDate());
        steamGameDto.setMetaCritic(steamGame.getMetaCritic());

        return steamGameDto;
    }

    private SteamGame mapToEntity(SteamGameDto steamGameDto) {
        SteamGame steamGame = new SteamGame();
        steamGame.setAppId(steamGameDto.getAppId());
        steamGame.setName(steamGameDto.getName());
        steamGame.setReleasedDate(steamGameDto.getReleasedDate());
        steamGame.setMetaCritic(steamGameDto.getMetaCritic());

        return steamGame;
    }

    @Override
    public Object getGameByName(String name) {
        return name;
    }

    @Override
    public SteamGameDto createGame(SteamGameDto steamGameDto) {
        SteamGame game = new SteamGame();
        game.setName(game.getName());
        game.setReleasedDate(game.getReleasedDate());
        game.setMetaCritic(game.getMetaCritic());

        SteamGame newGame = steamGameRepository.save(game);

        SteamGameDto gameResponse = new SteamGameDto();
        gameResponse.setAppId(newGame.getAppId());
        gameResponse.setName(newGame.getName());
        gameResponse.setReleasedDate(newGame.getReleasedDate());
        gameResponse.setMetaCritic(newGame.getMetaCritic());

        return gameResponse;
    }

    @Override
    public SteamGameDto getGameById(int id) {
        SteamGame game = steamGameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game not found"));
        return mapToDto(game);
    }

    @Override
    public SteamGameDto updateGame(SteamGameDto steamGameDto, int id) {
        SteamGame game = steamGameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game not found"));

        game.setName(steamGameDto.getName());
        game.setReleasedDate(steamGameDto.getReleasedDate());
        game.setMetaCritic(steamGameDto.getMetaCritic());

        SteamGame updatedGame = steamGameRepository.save(game);
        return mapToDto(updatedGame);
    }

    @Override
    public void deleteGameById(int id) {
        SteamGame game = steamGameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game not found"));
        steamGameRepository.delete(game);
    }
}
