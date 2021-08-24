package com.qa.choonz.unittest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.service.PlaylistService;


@SpringBootTest
public class PlaylistServiceTest {
	
	@MockBean
	private PlaylistRepository repo;
	
	@Autowired
	private PlaylistService service;
	
	private User user = new User(0L, "username", "real name", "password", new ArrayList<>());
	private Playlist playlist = new Playlist(0L, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	private PlaylistDTO playlistDTO = new PlaylistDTO(0L, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	private Optional<Playlist> optionalPlaylist = Optional.of(new Playlist(0L, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user));
	private Playlist newPlaylist = new Playlist(0L, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	private PlaylistDTO newPlaylistDTO = new PlaylistDTO(0L, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	
	@Test
	public void PlaylistCreateTest() {
		
		Mockito.when(this.repo.save(playlist)).thenReturn(playlist);
		
		assertThat(playlistDTO).isEqualTo(this.service.create(playlist));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(playlist);
	}
	
	@Test
	public void PlaylistReadAllTest() {
		
		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());
		
		assertThat(new ArrayList<>()).isEqualTo(this.service.read());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void PlaylistReadByIdTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalPlaylist);
		
		assertThat(playlistDTO).isEqualTo(this.service.read(0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void PlaylistUpdateTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalPlaylist);
		Mockito.when(this.repo.save(newPlaylist)).thenReturn(newPlaylist);
		
		assertThat(newPlaylistDTO).isEqualTo(this.service.update(newPlaylist, 0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newPlaylist);
	}
	
	@Test
	public void PlaylistDeleteTest() {
		assertThat(true).isEqualTo(this.service.delete(0L));
	}

}
