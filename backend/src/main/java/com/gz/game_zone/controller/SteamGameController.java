package com.gz.game_zone.controller;

import com.gz.game_zone.dto.GameDto;
import com.gz.game_zone.dto.GameSummaryDto;
import com.gz.game_zone.dto.SteamGameDto;
import com.gz.game_zone.dto.SteamGameResponse;
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
    public ResponseEntity<SteamGameResponse> getGames(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(service.getAllGame(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<SteamGameDto> gameDetail(@PathVariable int id) {
        return new ResponseEntity<>(service.getGameById(id), HttpStatus.OK);
    }

    @GetMapping("/by-genre")
    public ResponseEntity<List<GameSummaryDto>> getByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(service.getByGenre(genre));
    }

    @GetMapping("/by-tag")
    public ResponseEntity<List<GameSummaryDto>> getByTag(@RequestParam String tag) {
        return ResponseEntity.ok(service.getByTag(tag));
    }

    @GetMapping("/by-developer")
    public ResponseEntity<List<GameSummaryDto>> getByDeveloper(@RequestParam String developer) {
        return ResponseEntity.ok(service.getByDeveloper(developer));
    }

    @GetMapping("/by-publisher")
    public ResponseEntity<List<GameSummaryDto>> getByPublisher(@RequestParam String publisher) {
        return ResponseEntity.ok(service.getByPublisher(publisher));
    }

    @GetMapping("/{appid}")
    public ResponseEntity<GameDto> getOneGameById(@PathVariable int appid) {
        return ResponseEntity.ok(service.getGame(appid));
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
