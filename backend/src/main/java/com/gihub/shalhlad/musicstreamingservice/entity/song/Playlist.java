package com.gihub.shalhlad.musicstreamingservice.entity.song;

import com.gihub.shalhlad.musicstreamingservice.entity.BaseEntity;
import com.gihub.shalhlad.musicstreamingservice.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "playlists")
@Getter
@Setter
@NoArgsConstructor
public class Playlist extends BaseEntity<Long> {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "playlist_songs",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
