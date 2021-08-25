DROP TABLE IF EXISTS `playlist_track`;
DROP TABLE IF EXISTS `track`;
DROP TABLE IF EXISTS `playlist`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `album`;
DROP TABLE IF EXISTS `genre`;
DROP TABLE IF EXISTS `artist`;

CREATE TABLE IF NOT EXISTS `artist` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `genre` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `album` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `cover` VARCHAR(255),
    `name` VARCHAR(255) NOT NULL,
	`artist_id` BIGINT NOT NULL,
	`genre_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
	CONSTRAINT fk_album_artist FOREIGN KEY (artist_id) REFERENCES artist(id),
	CONSTRAINT fk_album_genre FOREIGN KEY (genre_id) REFERENCES genre(id)
);

CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
	`username` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `playlist` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
	`artwork` VARCHAR(255) NOT NULL,
	`description` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
	CONSTRAINT fk_playlist_user FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS `track` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `duration` INT NOT NULL,
	`lyrics` VARCHAR(255) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
    `album_id` BIGINT NOT NULL,
	`playlist_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
	CONSTRAINT fk_track_album FOREIGN KEY (album_id) REFERENCES album(id),
	CONSTRAINT fk_track_playlist FOREIGN KEY (playlist_id) REFERENCES playlist(id)
);

CREATE TABLE IF NOT EXISTS `playlist_track` (
	`playlist_id` BIGINT NOT NULL,
	`track_id` BIGINT NOT NULL,
    PRIMARY KEY (`playlist_id`, `track_id`),
	CONSTRAINT fk_playlist_track_playlist FOREIGN KEY (playlist_id) REFERENCES playlist(id),
	CONSTRAINT fk_playlist_track_track FOREIGN KEY (track_id) REFERENCES track(id)
);