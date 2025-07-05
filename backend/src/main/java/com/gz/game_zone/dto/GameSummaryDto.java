package com.gz.game_zone.dto;

public record GameSummaryDto(
        Integer appid,
        String name,
        Double metacritic,
        String boxImage
) {
}
