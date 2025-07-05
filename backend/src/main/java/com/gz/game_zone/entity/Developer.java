package com.gz.game_zone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "developer")
public class Developer {
    @Id
    @Column(name = "dev_id")
    private Integer id;
    @Column(name = "dev_name")
    private String name;
}
