INSERT INTO `artist` (`name`) VALUES ('Jack Montano');
INSERT INTO `genre` (`name`, `description`) VALUES ('Jazz', 'Jazz genre');
INSERT INTO `album` (`cover`, `name`, `artist_id`, `genre_id`) VALUES ('image', 'Blackpool', 1, 1);
INSERT INTO `user` (`name`, `password`, `username`) VALUES ('Micheal', 'password123', 'polkadot');
INSERT INTO `playlist` (`artwork`, `description`, `name`, `user_id`) VALUES ('image', 'The best playlist', 'My playlist', 1);
INSERT INTO `track` (`duration`, `lyrics`, `name`, `album_id`) VALUES (180, 'la la la land', 'Parkour', 1);
INSERT INTO `track` (`id`, `duration`, `lyrics`, `name`, `album_id`) VALUES (50, 210, 'la land', 'Parkour 2', 1);
INSERT INTO `playlist_track` (`playlist_id`, `track_id`) VALUES (1, 1);

