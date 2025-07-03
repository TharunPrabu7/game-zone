package com.gz.game_zone.dto;

import java.util.List;

public class SteamGameResponse {
    private List<SteamGameDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
