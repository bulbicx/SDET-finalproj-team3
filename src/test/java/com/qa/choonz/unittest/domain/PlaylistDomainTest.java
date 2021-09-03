package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.PublicUser;

public class PlaylistDomainTest {


	@Test
	public void PlaylistTest() {
		byte[] byteImage = new byte[1];
		byteImage[0] = 1;
		Image image = new Image(0L, "johnpng", "png", byteImage);
		PublicUser user = new PublicUser(0L, "username", "real name", "password", new ArrayList<>(), new ArrayList<>());
		Playlist playlist = new Playlist(0L, "playlist name", "playlist desc", image, new ArrayList<>(), user);
		assertThat("Playlist [id=0, name=playlist name, description=playlist desc, artwork="+image.toString()+", tracks=[], user="+user.toString()+"]").isEqualTo(playlist.toString());
	}
	

}
