package com.gz.game_zone.dto;

import java.time.LocalDate;
import java.util.List;

public record GameDto(
        Integer appid,
        String name,
        LocalDate releasedDate,
        Float metacritic,
        String description,
        String boxImage,
        String backgroundImage,
        List<String> genres,
        List<String> tags,
        List<String> developers,
        List<String> publishers
) {}

