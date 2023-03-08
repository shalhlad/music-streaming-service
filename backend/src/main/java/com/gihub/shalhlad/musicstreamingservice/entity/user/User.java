package com.gihub.shalhlad.musicstreamingservice.entity.user;

import com.gihub.shalhlad.musicstreamingservice.entity.BaseEntity;
import com.gihub.shalhlad.musicstreamingservice.entity.song.Album;
import com.gihub.shalhlad.musicstreamingservice.entity.song.Playlist;
import com.gihub.shalhlad.musicstreamingservice.entity.song.Song;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity<Long> {
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "encrypted_password", nullable = false)
    private String encryptedPassword;

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    private List<Role> roles;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @OneToMany(mappedBy = "user")
    private List<Playlist> playlists;

    @ManyToMany
    @JoinTable(name = "user_liked_songs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> likedSongs;

    @ManyToMany
    @JoinTable(name = "user_liked_albums",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private List<Album> likedAlbums;
}
