package com.gz.game_zone.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
    void deleteByName(String gameName);
    Optional<Game> findByName(String name);
}
