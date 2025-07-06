package com.gz.game_zone.repo;

import com.gz.game_zone.dto.GameSummaryDto;
import com.gz.game_zone.entity.SteamGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // Find by Genre
    @Query(value = """
        SELECT gd.appid, gd.name, gd.metacritic, gd.released_date
        FROM game_details gd
        JOIN game_genre gg ON gd.appid = gg.appid
        JOIN genre g ON gg.genre_id = g.genre_id
        WHERE LOWER(TRIM(g.genre_name)) LIKE LOWER(:genreName);
        """, nativeQuery = true)
    List<GameSummaryDto> findByGenreName(@Param("genreName") String genreName);


    // Find by Tag
    @Query(value = """
        SELECT gd.appid, gd.name, gd.metacritic, gd.released_date
        FROM game_details gd
        JOIN game_tag gt ON gd.appid = gt.appid
        JOIN tag t ON gt.tag_id = t.tag_id
        WHERE LOWER(TRIM(t.tag_name)) LIKE LOWER(:tagName);
        """, nativeQuery = true)
    List<GameSummaryDto> findByTagName(@Param("tagName") String tagName);


    // Find by Developer
    @Query(value = """
        SELECT gd.appid, gd.name, gd.metacritic, gd.released_date
        FROM game_details gd
        JOIN game_developer gdv ON gd.appid = gdv.appid
        JOIN developer d ON gdv.dev_id = d.dev_id
        WHERE LOWER(TRIM(d.dev_name)) LIKE CONCAT('%', LOWER(:devName), '%');
        """, nativeQuery = true)
    List<GameSummaryDto> findByDeveloperName(@Param("devName") String devName);


    // Find by Publisher
    @Query(value = """
        SELECT gd.appid, gd.name, gd.metacritic, gd.released_date
        FROM game_details gd
        JOIN game_publisher gp ON gd.appid = gp.appid
        JOIN publisher p ON gp.pub_id = p.pub_id
        WHERE LOWER(TRIM(p.pub_name)) LIKE CONCAT('%', LOWER(:pubName), '%');
        """, nativeQuery = true)
    List<GameSummaryDto> findByPublisherName(@Param("pubName") String pubName);

    // Find by genre with page
    @Query(value = """
        SELECT gd.appid,
               gd.name,
               gd.metacritic,
               gd.released_date
        FROM   game_details gd
        JOIN   game_genre   gg ON gd.appid = gg.appid
        JOIN   genre        g  ON gg.genre_id = g.genre_id
        WHERE  LOWER(TRIM(g.genre_name)) LIKE LOWER(CONCAT('%', :genreName, '%'))
        """,
            countQuery = """
        SELECT COUNT(*)
        FROM   game_details gd
        JOIN   game_genre   gg ON gd.appid = gg.appid
        JOIN   genre        g  ON gg.genre_id = g.genre_id
        WHERE  LOWER(TRIM(g.genre_name)) LIKE LOWER(CONCAT('%', :genreName, '%'))
        """,
            nativeQuery = true)
    Page<GameSummaryDto> findByGenreNameWithPage(@Param("genreName") String genreName,
                                                          Pageable pageable);

    // Find by tag with page
    @Query(value = """
        SELECT gd.appid,
               gd.name,
               gd.metacritic,
               gd.released_date
        FROM   game_details gd
        JOIN   game_tag   gg ON gd.appid = gg.appid
        JOIN   tag        g  ON gg.tag_id = g.tag_id
        WHERE  LOWER(TRIM(g.tag_name)) LIKE LOWER(CONCAT('%', :tagName, '%'))
        """,
            countQuery = """
        SELECT COUNT(*)
        FROM   game_details gd
        JOIN   game_tag   gg ON gd.appid = gg.appid
        JOIN   tag        g  ON gg.tag_id = g.tag_id
        WHERE  LOWER(TRIM(g.tag_name)) LIKE LOWER(CONCAT('%', :tagName, '%'))
        """,
            nativeQuery = true)
    Page<GameSummaryDto> findByTagNameWithPage(@Param("tagName") String tagName,
                                                 Pageable pageable);

    // Find by developer with page
    @Query(value = """
        SELECT gd.appid,
               gd.name,
               gd.metacritic,
               gd.released_date
        FROM   game_details gd
        JOIN   game_developer   gg ON gd.appid = gg.appid
        JOIN   developer        g  ON gg.dev_id = g.dev_id
        WHERE  LOWER(TRIM(g.dev_name)) LIKE LOWER(CONCAT('%', :devName, '%'))
        """,
            countQuery = """
        SELECT COUNT(*)
        FROM   game_details gd
        JOIN   game_developer   gg ON gd.appid = gg.appid
        JOIN   developer        g  ON gg.dev_id = g.dev_id
        WHERE  LOWER(TRIM(g.dev_name)) LIKE LOWER(CONCAT('%', :devName, '%'))
        """,
            nativeQuery = true)
    Page<GameSummaryDto> findByDeveloperNameWithPage(@Param("devName") String devName,
                                                 Pageable pageable);

    // Find by publisher with page
    @Query(value = """
        SELECT gd.appid,
               gd.name,
               gd.metacritic,
               gd.released_date
        FROM   game_details gd
        JOIN   game_publisher   gg ON gd.appid = gg.appid
        JOIN   publisher        g  ON gg.pub_id = g.pub_id
        WHERE  LOWER(TRIM(g.genre_name)) LIKE LOWER(CONCAT('%', :pubName, '%'))
        """,
            countQuery = """
        SELECT COUNT(*)
        FROM   game_details gd
        JOIN   game_publisher   gg ON gd.appid = gg.appid
        JOIN   publisher        g  ON gg.pub_id = g.pub_id
        WHERE  LOWER(TRIM(g.pub_name)) LIKE LOWER(CONCAT('%', :pubName, '%'))
        """,
            nativeQuery = true)
    Page<GameSummaryDto> findByPublisherNameWithPage(@Param("pubName") String pubName,
                                                 Pageable pageable);

}
