package com.gz.game_zone.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SteamGameDto {
    private Integer appId;
    private String name;
    private LocalDate releasedDate;
    private Float metaCritic;

}
