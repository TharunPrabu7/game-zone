package com.gz.game_zone.controller;

import com.gz.game_zone.entity.Game;
import com.gz.game_zone.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "api/v1/game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getGames(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String gameStudio,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Float rating,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order
    ) {
        return gameService.getFilteredGames(name, gameStudio, genre, year, rating, sortBy, order, page, size);
    }

    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody Game game){
        Game createdGame = gameService.addGame(game);
        return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Game> updateGame(
            @PathVariable String name,
            @RequestBody Game game){
        try {
            Game updatedGame = gameService.updateGame(name, game);
            return new ResponseEntity<>(updatedGame, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{gameName}")
    public ResponseEntity<String> deleteGame(@PathVariable String gameName){
        gameService.deleteGame(gameName);
        return new ResponseEntity<>("Game successfully deleted!", HttpStatus.OK);
    }

}
