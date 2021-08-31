package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.User;

public class PlaylistDomainTest {
	
	private User user = new User(0L, "username", "real name", "password", new ArrayList<>());
	private Playlist playlist = new Playlist(0L, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	
	@Test
	public void PlaylistTest() {
		assertThat("Playlist [id=0, name=playlist name, description=playlist desc, artwork=artwork, tracks=[], user="+user.toString()+"]").isEqualTo(playlist.toString());
		
	}
	
	@Test
	public void PlaylistTest2() {
		Playlist altPlaylist = new Playlist(0L, "playlist name", "playlist desc", "artwork", user);
		assertThat("Playlist [id=0, name=playlist name, description=playlist desc, artwork=artwork, tracks=null, user="+user.toString()+"]").isEqualTo(altPlaylist.toString());
		
	}
	
	@Test
	public void PlaylistTest3() {
		Playlist altPlaylist = new Playlist("playlist name", "playlist desc", "artwork", user);
		assertThat("Playlist [id=null, name=playlist name, description=playlist desc, artwork=artwork, tracks=null, user="+user.toString()+"]").isEqualTo(altPlaylist.toString());
		
	}
	
	@Test
	public void PlaylistTest4() {
		Playlist altPlaylist = new Playlist("playlist name", "playlist desc", "artwork");
		assertThat("Playlist [id=null, name=playlist name, description=playlist desc, artwork=artwork, tracks=null, user=null]").isEqualTo(altPlaylist.toString());
		
	}

}
