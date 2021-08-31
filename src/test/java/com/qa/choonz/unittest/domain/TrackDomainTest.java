package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;

public class TrackDomainTest {
	
	private Genre genre = new Genre(0L, "genre name", "genre desc", new ArrayList<>());
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>());
	private Album album = new Album(0L, "album name",  new ArrayList<>(), artist, genre, "cover");
	private Track track = new Track(0L, "track name", album, new ArrayList<>(), 120, "lyrics");
	
	@Test
	public void TrackTest() {
		assertThat("Track [id=0, name=track name, album="+album.toString()+", playlist=[], duration=120, lyrics=lyrics]").isEqualTo(track.toString());
		
	}
	
	@Test
	public void TrackTest2() {
		Track altTrack = new Track(0L, "track name", album, 120, "lyrics");
		assertThat("Track [id=0, name=track name, album="+album.toString()+", playlist=null, duration=120, lyrics=lyrics]").isEqualTo(altTrack.toString());
		
	}
	
	@Test
	public void TrackTest3() {
		Track altTrack = new Track("track name", album, 120, "lyrics");
		assertThat("Track [id=null, name=track name, album="+album.toString()+", playlist=null, duration=120, lyrics=lyrics]").isEqualTo(altTrack.toString());
		
	}

}
