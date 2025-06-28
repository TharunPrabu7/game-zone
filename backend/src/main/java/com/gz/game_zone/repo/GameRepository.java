package com.gz.game_zone.repo;

import com.gz.game_zone.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
    void deleteByName(String name);
    Optional<Game> findByName(String name);
}
