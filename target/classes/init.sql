-- CREATE TABLE IF NOT EXISTS _____ ();
-- AUTOINCREMENT
CREATE TABLE IF NOT EXISTS scoreboard (
 id INT PRIMARY KEY AUTO_INCREMENT,
 score INT NOT NULL DEFAULT 0,
 uuid VARCHAR(128) NOT NULL,
 username VARCHAR(128) NOT NULL
);