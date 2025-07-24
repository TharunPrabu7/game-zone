package com.gz.game_zone.controller;

import com.gz.game_zone.dto.*;
import com.gz.game_zone.service.SteamGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/game")
public class SteamGameController {

    @Autowired
    private SteamGameService service;

    // Get everything
    @GetMapping
    public ResponseEntity<SteamGameResponse> getGames(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(service.getAllGame(pageNo, pageSize), HttpStatus.OK);
    }

    // Get app id, name, meta critic score and released data of a game from its id
    @GetMapping("/{id}")
    public ResponseEntity<SteamGameDto> gameDetail(@PathVariable int id) {
        return new ResponseEntity<>(service.getGameById(id), HttpStatus.OK);
    }

    // Filter by genre, tag, developer and publisher
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

    // Filter by genre, tag, developer and publisher with pagination
    @GetMapping("/by-genre-page")
    public ResponseEntity<PagedResponse<GameSummaryDto>> getByGenreWithPage(
            @RequestParam String genre,
            @RequestParam int pageNo,
            @RequestParam int pageSize) {
        return ResponseEntity.ok(service.getByGenreWithPagination(genre, pageNo, pageSize));
    }
    @GetMapping("/by-tag-page")
    public ResponseEntity<PagedResponse<GameSummaryDto>> getByTagWithPage(
            @RequestParam String tag,
            @RequestParam int pageNo,
            @RequestParam int pageSize) {
        return ResponseEntity.ok(service.getByTagWithPagination(tag, pageNo, pageSize));
    }
    @GetMapping("/by-developer-page")
    public ResponseEntity<PagedResponse<GameSummaryDto>> getByDeveloperWithPage(
            @RequestParam String developer,
            @RequestParam int pageNo,
            @RequestParam int pageSize) {
        return ResponseEntity.ok(service.getByDeveloperWithPagination(developer, pageNo, pageSize));
    }
    @GetMapping("/by-publisher-page")
    public ResponseEntity<PagedResponse<GameSummaryDto>> getByPublisher(
            @RequestParam String publisher,
            @RequestParam int pageNo,
            @RequestParam int pageSize) {
        return ResponseEntity.ok(service.getByPublisherWithPagination(publisher, pageNo, pageSize));
    }

    // Get sorted list by meta critic score
    @GetMapping("/sort/{field}")
    public ResponseEntity<List<SteamGameDto>> getGameWithSort(@PathVariable String field) {
        List<SteamGameDto> allGamesSorted = service.findGameWithSorting(field);
        return ResponseEntity.ok(allGamesSorted);
    }

    // Get every detail about a game using its id
    @GetMapping("/details/{appid}")
    public ResponseEntity<GameDto> getOneGameById(@PathVariable int appid) {
        return ResponseEntity.ok(service.getGame(appid));
    }

    // Add a game
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SteamGameDto> addGame(@RequestBody SteamGameDto steamGameDto) {
        return new ResponseEntity<>(service.createGame(steamGameDto), HttpStatus.CREATED);
    }

    // Update the game using its id
    @PutMapping("/{id}/update")
    public ResponseEntity<SteamGameDto> updateGame(@RequestBody SteamGameDto steamGameDto, @PathVariable("id") int appId) {
        SteamGameDto response = service.updateGame(steamGameDto, appId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete the game using its id
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteGame(@PathVariable("id") int appId) {
        service.deleteGameById(appId);
        return ResponseEntity.ok("Game deleted successfully.");
    }
}
