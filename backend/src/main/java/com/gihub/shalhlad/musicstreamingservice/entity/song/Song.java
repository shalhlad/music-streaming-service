package com.gihub.shalhlad.musicstreamingservice.entity.song;

import com.gihub.shalhlad.musicstreamingservice.entity.BaseEntity;
import com.gihub.shalhlad.musicstreamingservice.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "songs")
@Getter
@Setter
@NoArgsConstructor
public class Song extends BaseEntity<Long> {
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @ManyToMany(mappedBy = "likedSongs")
    private Set<User> likes;

    @Column(name = "plays", nullable = false)
    private Integer plays = 0;

    @Column(name = "release_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;

    @ManyToMany
    @JoinTable(name = "song_to_genre",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;
}
