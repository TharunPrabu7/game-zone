package com.gz.game_zone.dto;

import java.util.Date;

public record GameSummaryDto(
        Integer appid,
        String name,
        Float metacritic,
        Date released_date
) {
}
