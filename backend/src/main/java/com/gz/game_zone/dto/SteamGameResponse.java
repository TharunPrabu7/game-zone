package com.gz.game_zone.dto;

import lombok.Data;

import java.util.List;

@Data
public class SteamGameResponse {
    private List<SteamGameDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
