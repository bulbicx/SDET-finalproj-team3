INSERT INTO `image` (`name`, `pic_byte`, `type`) VALUES ('johnpng', '57', 'png');
INSERT INTO `artist` (`name`, `image_id`) VALUES ('Jack Montano', 1);
INSERT INTO `genre` (`name`, `description`, `image_id`) VALUES ('Jazz', 'Jazz genre', 1);
INSERT INTO `album` (`cover`, `name`, `artist_id`, `genre_id`, `image_id`) VALUES ('image', 'Blackpool', 1, 1, 1);
INSERT INTO `user` (`dtype`, `name`, `password`, `username`) VALUES ('publicuser', 'Micheal', 'password123', 'polkadot');
INSERT INTO `user` (`dtype`, `name`, `password`, `username`) VALUES ('adminuser', 'John', 'password123', 'polkaot');
INSERT INTO `playlist` (`artwork`, `description`, `name`, `user_id`, `image_id`) VALUES ('image', 'The best playlist', 'My playlist', 1, 1);
INSERT INTO `track` (`duration`, `lyrics`, `name`, `album_id`) VALUES (180, 'la la la land', 'Parkour', 1);
INSERT INTO `track` (`id`, `duration`, `lyrics`, `name`, `album_id`) VALUES (50, 210, 'la land', 'Parkour 2', 1);
INSERT INTO `playlist_track` (`playlist_id`, `track_id`) VALUES (1, 1);


