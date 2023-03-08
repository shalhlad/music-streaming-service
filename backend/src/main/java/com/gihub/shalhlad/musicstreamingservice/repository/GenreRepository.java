package com.gihub.shalhlad.musicstreamingservice.repository;

import com.gihub.shalhlad.musicstreamingservice.entity.song.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
