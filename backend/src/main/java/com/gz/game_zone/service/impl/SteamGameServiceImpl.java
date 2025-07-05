package com.gz.game_zone.service.impl;

import com.gz.game_zone.dto.GameDto;
import com.gz.game_zone.dto.GameSummaryDto;
import com.gz.game_zone.dto.SteamGameDto;
import com.gz.game_zone.dto.SteamGameResponse;
import com.gz.game_zone.entity.*;
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
                .filter(g -> metaCritic == null || (g.getMetacritic() != null && g.getMetacritic() >= metaCritic))
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
    public SteamGameResponse getAllGame(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<SteamGame> games = steamGameRepository.findAll(pageable);
        List<SteamGame> listOfGames = games.getContent();
        List<SteamGameDto> content = listOfGames.stream().map(this::mapToDto).toList();

        SteamGameResponse gameResponse = new SteamGameResponse();
        gameResponse.setContent(content);
        gameResponse.setPageNo(games.getNumber());
        gameResponse.setPageSize(games.getSize());
        gameResponse.setTotalPages(games.getTotalPages());
        gameResponse.setTotalElements(games.getTotalElements());
        gameResponse.setLast(gameResponse.isLast());

        return gameResponse;
    }

    private SteamGameDto mapToDto(SteamGame steamGame) {
        SteamGameDto steamGameDto = new SteamGameDto();
        steamGameDto.setAppId(steamGame.getAppid());
        steamGameDto.setName(steamGame.getName());
        steamGameDto.setReleasedDate(steamGame.getReleasedDate());
        steamGameDto.setMetaCritic(steamGame.getMetacritic());

        return steamGameDto;
    }

    private SteamGame mapToEntity(SteamGameDto steamGameDto) {
        SteamGame steamGame = new SteamGame();
        steamGame.setAppid(steamGameDto.getAppId());
        steamGame.setName(steamGameDto.getName());
        steamGame.setReleasedDate(steamGameDto.getReleasedDate());
        steamGame.setMetacritic(steamGameDto.getMetaCritic());

        return steamGame;
    }

    // Filters by genre
    public List<GameSummaryDto> getByGenre(String genre) {
        return steamGameRepository.findByGenreName(genre);
    }

    // Filters by tag
    public List<GameSummaryDto> getByTag(String tag) {
        return steamGameRepository.findByTagName(tag);
    }

    // Filters by developers
    public List<GameSummaryDto> getByDeveloper(String developer) {
        return steamGameRepository.findByDeveloperName(developer);
    }

    // Filters by publishers
    public List<GameSummaryDto> getByPublisher(String publisher) {
        return steamGameRepository.findByPublisherName(publisher);
    }

    @Override
    public Object getGameByName(String name) {
        return name;
    }

    @Override
    public GameDto getGame(Integer appid) {
        SteamGame g = steamGameRepository.findByAppid(appid)
                .orElseThrow(() ->
                        new GameNotFoundException("Game " + appid + " not found"));

        return new GameDto(
                g.getAppid(),
                g.getName(),
                g.getReleasedDate(),
                g.getMetacritic(),
                g.getDescription(),
                g.getBoxImage(),
                g.getBackgroundImage(),
                g.getGenres().stream()
                        .map(Genre::getName)
                        .sorted()
                        .toList(),
                g.getTags().stream()
                        .map(Tag::getName)
                        .sorted()
                        .toList(),
                g.getDevelopers().stream()
                        .map(Developer::getName)
                        .sorted()
                        .toList(),
                g.getPublishers().stream()
                        .map(Publisher::getName)
                        .sorted()
                        .toList()
        );
    }

    @Override
    public GameDto getGameByGenre(String genre) {

        return null;
    }

    // Add a gama
    @Override
    public SteamGameDto createGame(SteamGameDto steamGameDto) {
        SteamGame game = new SteamGame();
        game.setName(game.getName());
        game.setReleasedDate(game.getReleasedDate());
        game.setMetacritic(game.getMetacritic());

        SteamGame newGame = steamGameRepository.save(game);

        SteamGameDto gameResponse = new SteamGameDto();
        gameResponse.setAppId(newGame.getAppid());
        gameResponse.setName(newGame.getName());
        gameResponse.setReleasedDate(newGame.getReleasedDate());
        gameResponse.setMetaCritic(newGame.getMetacritic());

        return gameResponse;
    }

    @Override
    public SteamGameDto getGameById(int id) {
        SteamGame game = steamGameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game not found"));
        return mapToDto(game);
    }

    // Update a game
    @Override
    public SteamGameDto updateGame(SteamGameDto steamGameDto, int id) {
        SteamGame game = steamGameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game not found"));

        game.setName(steamGameDto.getName());
        game.setReleasedDate(steamGameDto.getReleasedDate());
        game.setMetacritic(steamGameDto.getMetaCritic());

        SteamGame updatedGame = steamGameRepository.save(game);
        return mapToDto(updatedGame);
    }

    // Delete a game
    @Override
    public void deleteGameById(int id) {
        SteamGame game = steamGameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game not found"));
        steamGameRepository.delete(game);
    }
}
