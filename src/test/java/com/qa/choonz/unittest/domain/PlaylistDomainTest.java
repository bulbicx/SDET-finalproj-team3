package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.PublicUser;

public class PlaylistDomainTest {

	private PublicUser user = new PublicUser(0L, "username", "real name", "password", new ArrayList<>());
	private Playlist playlist = new Playlist(0L, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	private Image image = new Image(0L, "image name", "image type", null);
	private PublicUser user = new PublicUser(0L, "username", "real name", "password", new ArrayList<>(), new ArrayList<>());
	private Playlist playlist = new Playlist(0L, "playlist name", "playlist desc", image, new ArrayList<>(), user);

	
	@Test
	public void PlaylistTest() {
		assertThat("Playlist [id=0, name=playlist name, description=playlist desc, artwork="+image.toString()+", tracks=[], user="+user.toString()+"]").isEqualTo(playlist.toString());
		
	}
	

}
