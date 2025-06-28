package com.gz.game_zone.service.impl;

import com.gz.game_zone.dto.GamesDto;
import com.gz.game_zone.entity.Game;
import com.gz.game_zone.repo.GameRepository;
import com.gz.game_zone.service.GameService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @Override
    public Optional<Game> getGameByName(String name){
        return gameRepository.findByName(name);
    }

    @Override
    public List<Game> getFilteredGames(String name, String genre, LocalDate releaseDate,
                                       Long copiesSold, Float rating, Boolean isGameOfTheYear,
                                       String gameStudios, Long revenue) {
        return gameRepository.findAll().stream()
                .filter(game -> name == null || game.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(game -> genre == null || game.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .filter(game -> releaseDate == null || game.getReleasedDate().equals(releaseDate))
                .filter(game -> copiesSold == null || game.getCopiesSold() >= copiesSold)
                .filter(game -> rating == null || game.getRating() >= rating)
                .filter(game -> isGameOfTheYear == null || game.getGameOfTheYear().equals(isGameOfTheYear))
                .filter(game -> gameStudios == null || game.getGameStudios().toLowerCase().contains(gameStudios.toLowerCase()))
                .filter(game -> revenue == null || game.getRevenue() >= revenue)
                .collect(Collectors.toList());
    }


    @Override
    public Game addGame(GamesDto gamesDto){
        Game game = new Game(
                gamesDto.getName(),
                gamesDto.getGenre(),
                gamesDto.getReleasedDate(),
                gamesDto.getCopiesSold(),
                gamesDto.getRating(),
                gamesDto.isGameOfTheYear(),
                gamesDto.getGameStudios(),
                gamesDto.getRevenue()
        );
        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(String name, GamesDto gamesDto){
        Optional<Game> optionalGame = gameRepository.findByName(name);
        if (optionalGame.isPresent()){
            Game game = new Game(
                    gamesDto.getName(),
                    gamesDto.getGenre(),
                    gamesDto.getReleasedDate(),
                    gamesDto.getCopiesSold(),
                    gamesDto.getRating(),
                    gamesDto.isGameOfTheYear(),
                    gamesDto.getGameStudios(),
                    gamesDto.getRevenue()
            );
            return gameRepository.save(game);
        } else {
            throw new NoSuchElementException("Game not found with name: " + name);
        }
    }

    @Override
    public void deleteGame(String name){
        Game game = gameRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Game not found"));
        gameRepository.delete(game);
    }
}
