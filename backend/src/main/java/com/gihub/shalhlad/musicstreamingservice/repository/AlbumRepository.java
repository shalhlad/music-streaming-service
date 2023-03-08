package com.gihub.shalhlad.musicstreamingservice.repository;

import com.gihub.shalhlad.musicstreamingservice.entity.song.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
}
