package com.gz.game_zone.repo;

import com.gz.game_zone.dto.GameSummaryDto;
import com.gz.game_zone.entity.SteamGame;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SteamGameRepository extends JpaRepository<SteamGame, Integer>{
    @EntityGraph(attributePaths = {
            "genres", "tags", "developers", "publishers"
    })
    Optional<SteamGame> findByAppid(Integer appid);

    // 1. Find by Genre
    @Query(value = """
        SELECT gd.appid, gd.name, gd.metacritic
        FROM game_details gd
        JOIN game_genre gg ON gd.appid = gg.appid
        JOIN genre g ON gg.genre_id = g.genre_id
        WHERE LOWER(TRIM(g.genre_name)) LIKE LOWER(:genreName);
        """, nativeQuery = true)
    List<GameSummaryDto> findByGenreName(@Param("genreName") String genreName);


    // 2. Find by Tag
    @Query(value = """
        SELECT gd.appid, gd.name, gd.metacritic
        FROM game_details gd
        JOIN game_tag gt ON gd.appid = gt.appid
        JOIN tag t ON gt.tag_id = t.tag_id
        WHERE LOWER(TRIM(t.tag_name)) LIKE LOWER(:tagName);
        """, nativeQuery = true)
    List<GameSummaryDto> findByTagName(@Param("tagName") String tagName);


    // 3. Find by Developer
    @Query(value = """
        SELECT gd.appid, gd.name, gd.metacritic
        FROM game_details gd
        JOIN game_developer gdv ON gd.appid = gdv.appid
        JOIN developer d ON gdv.dev_id = d.dev_id
        WHERE LOWER(TRIM(d.dev_name)) LIKE CONCAT('%', LOWER(:devName), '%');
        """, nativeQuery = true)
    List<GameSummaryDto> findByDeveloperName(@Param("devName") String devName);


    // 4. Find by Publisher
    @Query(value = """
        SELECT gd.appid, gd.name, gd.metacritic
        FROM game_details gd
        JOIN game_publisher gp ON gd.appid = gp.appid
        JOIN publisher p ON gp.pub_id = p.pub_id
        WHERE LOWER(TRIM(p.pub_name)) LIKE CONCAT('%', LOWER(:pubName), '%');
        """, nativeQuery = true)
    List<GameSummaryDto> findByPublisherName(@Param("pubName") String pubName);
}
