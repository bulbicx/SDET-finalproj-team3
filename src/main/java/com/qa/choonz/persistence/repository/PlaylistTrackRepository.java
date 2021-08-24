package com.qa.choonz.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.PlaylistTrack;

@Repository
public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, Long>{

}
