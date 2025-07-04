package com.gz.game_zone.controller;

import com.gz.game_zone.dto.SteamGameDto;
import com.gz.game_zone.entity.Game;
import com.gz.game_zone.entity.SteamGame;
import com.gz.game_zone.service.SteamGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class SteamGameController {

    @Autowired
    private SteamGameService service;

    @GetMapping("/game")
    public ResponseEntity<List<SteamGame>> getGames(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        List<SteamGame> games = service.getAll(pageNo, pageSize);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<SteamGameDto> gameDetail(@PathVariable int id) {
        return new ResponseEntity<>(service.getGameById(id), HttpStatus.OK);
    }

    @PostMapping("/game/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SteamGameDto> addGame(@RequestBody SteamGameDto steamGameDto) {
        return new ResponseEntity<>(service.createGame(steamGameDto), HttpStatus.CREATED);
    }

    @PutMapping("/game/{id}/update")
    public ResponseEntity<SteamGameDto> updateGame(@RequestBody SteamGameDto steamGameDto, @PathVariable("id") int appId) {
        SteamGameDto response = service.updateGame(steamGameDto, appId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/game/{id}/delete")
    public ResponseEntity<String> deleteGame(@PathVariable("id") int appId) {
        service.deleteGameById(appId);
        return ResponseEntity.ok("Game deleted successfully.");
    }
}
