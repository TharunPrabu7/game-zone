package com.gz.game_zone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "games_stat")
public class Game {
    @Id
    @Column(name = "game", unique = true)
    private String name;

    @Column(name = "genre")
    private String genre;

    @Column(name = "released_year")
    private LocalDate releasedDate;

    @Column(name = "copies_sold")
    private Long copiesSold;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "goty")
    private Boolean isGameOfTheYear;

    @Column(name = "game_studios")
    private String gameStudios;

    @Column(name = "revenue")
    private Long revenue;

    public Game() {
    }

    public Game(String name, String genre, LocalDate releasedDate, Long copiesSold, Float rating, Boolean isGameOfTheYear, String gameStudios, Long revenue) {
        this.name = name;
        this.genre = genre;
        this.releasedDate = releasedDate;
        this.copiesSold = copiesSold;
        this.rating = rating;
        this.isGameOfTheYear = isGameOfTheYear;
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

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Long getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(Long copiesSold) {
        this.copiesSold = copiesSold;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getGameOfTheYear() {
        return isGameOfTheYear;
    }

    public void setGameOfTheYear(Boolean gameOfTheYear) {
        isGameOfTheYear = gameOfTheYear;
    }

    public String getGameStudios() {
        return gameStudios;
    }

    public void setGameStudios(String gameStudios) {
        this.gameStudios = gameStudios;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(name, game.name) && Objects.equals(genre, game.genre) && Objects.equals(releasedDate, game.releasedDate) && Objects.equals(copiesSold, game.copiesSold) && Objects.equals(rating, game.rating) && Objects.equals(isGameOfTheYear, game.isGameOfTheYear) && Objects.equals(gameStudios, game.gameStudios) && Objects.equals(revenue, game.revenue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, releasedDate, copiesSold, rating, isGameOfTheYear, gameStudios, revenue);
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releasedDate +
                ", copiesSold=" + copiesSold +
                ", rating=" + rating +
                ", isGameOfTheYear=" + isGameOfTheYear +
                ", gameStudios='" + gameStudios + '\'' +
                ", revenue=" + revenue +
                '}';
    }
}
