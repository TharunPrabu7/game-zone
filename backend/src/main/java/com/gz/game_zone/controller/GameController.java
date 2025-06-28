package com.gz.game_zone.controller;

import com.gz.game_zone.dto.GamesDto;
import com.gz.game_zone.entity.Game;
import com.gz.game_zone.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(path = "/api/v1/game")
public class GameController {


    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping(path = "/table")
    public List<Game> getGames(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) LocalDate releasedDate,
            @RequestParam(required = false) Long copiesSold,
            @RequestParam(required = false) Float rating,
            @RequestParam(required = false) Boolean isGameOfTheYear,
            @RequestParam(required = false) String gameStudio,
            @RequestParam(required = false) Long revenue
    ){
        return gameService.getFilteredGames(name, genre, releasedDate, copiesSold, rating, isGameOfTheYear, gameStudio, revenue);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Game> getGameByName(@PathVariable String name){
        return gameService.getGameByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Game> addGame(@RequestBody GamesDto gamesDto){
        Game createdGame = gameService.addGame(gamesDto);
        System.out.println(gamesDto);
        return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Game> updateGame(@RequestParam String name, @RequestBody GamesDto gamesDto){
        try {
            Game updatedGame = gameService.updateGame(name, gamesDto);
            return new ResponseEntity<>(updatedGame, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteGame(@RequestParam String name){
        try {
            gameService.deleteGame(name);
            return new ResponseEntity<>("Game Successfully Deleted", HttpStatus.OK);
        } catch(NoSuchElementException e) {
         return new ResponseEntity<>("No such game found", HttpStatus.NOT_FOUND);
        }
    }
}
