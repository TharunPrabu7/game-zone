package com.gz.game_zone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SteamGameDto {
    private Integer appId;
    private String name;
    private LocalDate releasedDate;
    private Float metaCritic;

}
