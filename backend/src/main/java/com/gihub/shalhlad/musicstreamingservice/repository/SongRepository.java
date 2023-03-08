package com.gihub.shalhlad.musicstreamingservice.repository;

import com.gihub.shalhlad.musicstreamingservice.entity.song.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
}
