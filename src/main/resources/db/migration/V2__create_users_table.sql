CREATE TABLE users (
  user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username varchar(45) NOT NULL UNIQUE,
  password varchar(64) NOT NULL,
  role varchar(45) NOT NULL,
  enabled int
);