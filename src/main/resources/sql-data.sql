INSERT INTO `image` (`name`, `pic_byte`, `type`) VALUES ('johnpng', '57', 'png');
INSERT INTO `artist` (`name`, `image_id`) VALUES ('Jack Montano', 1);
INSERT INTO `genre` (`name`, `description`, `image_id`) VALUES ('Jazz', 'Jazz genre', 1);
INSERT INTO `album` (`cover`, `name`, `artist_id`, `genre_id`, `image_id`) VALUES ('image', 'Blackpool', 1, 1, 1);
INSERT INTO `user` (`dtype`, `name`, `password`, `username`) VALUES ('PublicUser', 'Micheal', 'password123', 'polkadot');
INSERT INTO `user` (`dtype`, `name`, `password`, `username`) VALUES ('PublicUser', 'Micheal', 'password123', 'polkadot');
INSERT INTO `user` (`dtype`, `name`, `password`, `username`) VALUES ('AdminUser', 'John', 'password123', 'polkaot');
INSERT INTO `playlist` (`description`, `name`, `user_id`, `image_id`) VALUES ('The best playlist', 'My playlist', 1, 1);
INSERT INTO `track` (`duration`, `lyrics`, `name`, `album_id`) VALUES (180, 'la la la land', 'Parkour', 1);
INSERT INTO `track` (`id`, `duration`, `lyrics`, `name`, `album_id`) VALUES (50, 210, 'la land', 'Parkour 2', 1);
INSERT INTO `playlist_track` (`playlist_id`, `track_id`) VALUES (1, 1);
INSERT INTO `session` (`id`, `user_id`, `token`) VALUES (1, 2, '$31$11$Zhi4PT548-kYfpgwiOM8aE0EkCLkyHOQuKyUI_S1Fb0');
INSERT INTO `session` (`id`, `user_id`, `token`) VALUES (2, 1, '$31$11$eTW7By3kk0_UdPsmwrFWvyLLKwtEGDd-tVnSgrIJQ4Q');


