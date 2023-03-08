package com.gihub.shalhlad.musicstreamingservice.entity.song;

import com.gihub.shalhlad.musicstreamingservice.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre extends BaseEntity<Long> {
    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
