package com.gz.game_zone.controller;

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
}
