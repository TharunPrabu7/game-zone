package com.gz.game_zone.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public List<Game> getGame(){
        return gameRepository.findAll();
    }

    public List<Game> getGameFromStudios(String gameStudios){
        return gameRepository.findAll().stream()
                .filter(Game -> gameStudios.equals(Game.getGameStudios()))
                .collect(Collectors.toList());
    }

    public List<Game> getGameByName(String searchText){
        return gameRepository.findAll().stream()
                .filter(Game -> Game.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect((Collectors.toList()));
    }

    public List<Game> getGameByGenre(String genre){
        return gameRepository.findAll().stream()
                .filter(Game -> Game.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Game> getGotyGames(){
        return gameRepository.findAll().stream()
                .filter(Game::isGoty)
                .collect(Collectors.toList());
    }

    public List<Game> getGameByYear(int year){
        return gameRepository.findAll().stream()
                .filter(game -> game.getReleaseDate().getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Game> getGameByRating(float minRating){
        return gameRepository.findAll().stream()
                .filter(game -> game.getRating() >= minRating)
                .collect(Collectors.toList());
    }

    public List<Game> getGameSortedByCopiesSold(){
        return gameRepository.findAll().stream()
                .sorted(Comparator.comparingLong(Game::getCopiesSold).reversed())
                .collect(Collectors.toList());
    }

    public List<Game> getGameSortedByRevenue(){
        return gameRepository.findAll().stream()
                .sorted(Comparator.comparingLong(Game::getRevenue).reversed())
                .collect(Collectors.toList());
    }

    public long getTotalRevenue(){
        return gameRepository.findAll().stream()
                .mapToLong(Game::getRevenue)
                .sum();
    }

    public List<Game> getFilteredGames(String name, String gameStudio, String genre, Integer year,
                                       String sortBy, String order, int page, int size) {
        Stream<Game> stream = gameRepository.findAll().stream();

        if (name != null)
            stream = stream.filter(g -> g.getName().toLowerCase().contains(name.toLowerCase()));

        if (gameStudio != null)
            stream = stream.filter(g -> g.getGameStudios().toLowerCase().contains(gameStudio.toLowerCase()));

        if (genre != null)
            stream = stream.filter(g -> g.getGenre().toLowerCase().contains(genre.toLowerCase()));

        if (year != null)
            stream = stream.filter(g -> g.getReleaseDate().getYear() == year);

        // Sorting
        if (sortBy != null) {
            Comparator<Game> comparator = switch (sortBy) {
                case "name" -> Comparator.comparing(Game::getName);
                case "copiesSold" -> Comparator.comparingLong(Game::getCopiesSold);
                case "rating" -> Comparator.comparingDouble(Game::getRating);
                case "revenue" -> Comparator.comparingLong(Game::getRevenue);
                case "releaseDate" -> Comparator.comparing(Game::getReleaseDate);
                default -> null;
            };

            if (comparator != null) {
                if ("desc".equalsIgnoreCase(order)) {
                    comparator = comparator.reversed();
                }
                stream = stream.sorted(comparator);
            }
        }

        // Pagination
        return stream
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }


    public Game addGame(Game game){
        return gameRepository.save(game);
    }

    public Game updateGame(String name, Game updatedGame){
        return gameRepository.findByName(name)
                .map(game -> {
                    game.setGenre(updatedGame.getGenre());
                    game.setReleaseDate(updatedGame.getReleaseDate());
                    game.setCopiesSold(updatedGame.getCopiesSold());
                    game.setRating(updatedGame.getRating());
                    game.setGoty(updatedGame.isGoty());
                    game.setGameStudios(updatedGame.getGameStudios());
                    game.setRevenue(updatedGame.getRevenue());
                    return gameRepository.save(game);
                })
                .orElseThrow(() -> new NoSuchElementException("Game not found: " + name));
    }

    @Transactional
    public void deleteGame(String name){
        gameRepository.deleteByName(name);
    }


}
