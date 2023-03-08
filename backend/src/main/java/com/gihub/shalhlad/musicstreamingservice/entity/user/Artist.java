package com.gihub.shalhlad.musicstreamingservice.entity.user;

import com.gihub.shalhlad.musicstreamingservice.entity.BaseEntity;
import com.gihub.shalhlad.musicstreamingservice.entity.song.Album;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@jakarta.persistence.Entity
@Table(name = "artists")
@Getter
@Setter
@NoArgsConstructor
public class Artist extends BaseEntity<Long> {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "biography", nullable = false)
    private String biography;

    @ManyToMany
    @JoinTable(name = "artist_albums",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private List<Album> albums;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
