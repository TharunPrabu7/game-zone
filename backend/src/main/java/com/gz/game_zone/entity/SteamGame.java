package com.gz.game_zone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game_details")
public class SteamGame {

    @Id
    @Column(name = "appid")
    private Integer appid;

    @Column(name = "name")
    private String name;

    @Column(name = "released_date")
    private LocalDate releasedDate;

    private Float metacritic;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "box_image")
    private String boxImage;

    @Column(name = "background_image")
    private String backgroundImage;

    @ManyToMany
    @JoinTable(
            name               = "game_genre",
            joinColumns        = @JoinColumn(name = "appid"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name               = "game_tag",
            joinColumns        = @JoinColumn(name = "appid"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name               = "game_developer",
            joinColumns        = @JoinColumn(name = "appid"),
            inverseJoinColumns = @JoinColumn(name = "dev_id")
    )
    private Set<Developer> developers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name               = "game_publisher",
            joinColumns        = @JoinColumn(name = "appid"),
            inverseJoinColumns = @JoinColumn(name = "pub_id")
    )
    private Set<Publisher> publishers = new HashSet<>();
}
