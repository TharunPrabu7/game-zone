package com.gz.game_zone.repo;

import com.gz.game_zone.entity.SteamGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SteamGameRepository extends JpaRepository<SteamGame, Integer>{
}
