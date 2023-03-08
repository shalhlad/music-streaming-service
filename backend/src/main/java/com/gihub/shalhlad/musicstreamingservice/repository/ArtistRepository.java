package com.gihub.shalhlad.musicstreamingservice.repository;

import com.gihub.shalhlad.musicstreamingservice.entity.user.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
}
