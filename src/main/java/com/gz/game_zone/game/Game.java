package com.gz.game_zone.game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "games_stat")
public class Game {
    @Id
    @Column(name = "game", unique = true)
    private String name;
    private String genre;
    private LocalDateTime releaseDate;
    private long copiesSold;
    private float rating;
    private boolean goty;
    private String gameStudios;
    private long revenue;

    public Game() {
    }

    public Game(String name, String genre, LocalDateTime releaseDate, long copiesSold, float rating, boolean goty, String gameStudios, long revenue) {
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.copiesSold = copiesSold;
        this.rating = rating;
        this.goty = goty;
        this.gameStudios = gameStudios;
        this.revenue = revenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(long copiesSold) {
        this.copiesSold = copiesSold;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isGoty() {
        return goty;
    }

    public void setGoty(boolean goty) {
        this.goty = goty;
    }

    public String getGameStudios() {
        return gameStudios;
    }

    public void setGameStudios(String gameStudios) {
        this.gameStudios = gameStudios;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }
}
