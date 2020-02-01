DROP TABLE IF EXISTS user;

CREATE TABLE user (
   id bigint NOT NULL,
   first_name varchar(255) DEFAULT NULL,
   last_name varchar(255) DEFAULT NULL,
   PRIMARY KEY (id)
 );