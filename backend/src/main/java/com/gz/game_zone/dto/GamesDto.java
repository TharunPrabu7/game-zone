package com.gz.game_zone.dto;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Objects;

@Repository
public class GamesDto {
    private String name;
    private String genre;
    private LocalDate releasedDate;
    private Long copiesSold;
    private Float rating;
    private Boolean isGameOfTheYear;
    private String gameStudios;
    private Long revenue;

    public GamesDto() {
    }

    public GamesDto(String name, String genre, LocalDate releasedDate, long copiesSold, float rating, boolean isGameOfTheYear, String gameStudios, long revenue) {
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

    public boolean isGameOfTheYear() {
        return isGameOfTheYear;
    }

    public void setGameOfTheYear(boolean gameOfTheYear) {
        isGameOfTheYear = gameOfTheYear;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GamesDto gamesDto = (GamesDto) o;
        return copiesSold == gamesDto.copiesSold && Float.compare(rating, gamesDto.rating) == 0 && isGameOfTheYear == gamesDto.isGameOfTheYear && revenue == gamesDto.revenue && Objects.equals(name, gamesDto.name) && Objects.equals(genre, gamesDto.genre) && Objects.equals(releasedDate, gamesDto.releasedDate) && Objects.equals(gameStudios, gamesDto.gameStudios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, releasedDate, copiesSold, rating, isGameOfTheYear, gameStudios, revenue);
    }

    @Override
    public String toString() {
        return "GamesDto{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", releasedDate=" + releasedDate +
                ", copiesSold=" + copiesSold +
                ", rating=" + rating +
                ", isGameOfTheYear=" + isGameOfTheYear +
                ", gameStudios='" + gameStudios + '\'' +
                ", revenue=" + revenue +
                '}';
    }
}
