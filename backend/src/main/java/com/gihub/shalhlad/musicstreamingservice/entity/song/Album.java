package com.gihub.shalhlad.musicstreamingservice.entity.song;

import com.gihub.shalhlad.musicstreamingservice.entity.BaseEntity;
import com.gihub.shalhlad.musicstreamingservice.entity.user.Artist;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "albums")
@Getter
@Setter
@NoArgsConstructor
public class Album extends BaseEntity<Long> {
    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "album")
    private List<Song> songs;

    @ManyToMany(mappedBy = "albums")
    private List<Artist> artists;
}
