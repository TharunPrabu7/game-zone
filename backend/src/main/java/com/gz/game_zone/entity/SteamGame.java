package com.gz.game_zone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game_details_test")
public class SteamGame {
    @Id
    @Column(name = "appid")
    Integer appId;

    @Column(name = "name")
    String name;

    @Column(name = "released_date")
    LocalDate releasedDate;

    @Column(name = "metacritic")
    Float metaCritic;
}
