CREATE TABLE IF NOT EXISTS `bot_votes_user` (
	`id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`vote_id` int NOT NULL,
	`username` VARCHAR(255) NOT NULL,
	`type` ENUM('YES','NO') NOT NULL,
	`channel` VARCHAR(255) NOT NULL
);