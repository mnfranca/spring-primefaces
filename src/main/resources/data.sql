-- ROLE
INSERT INTO imo.role (name) VALUES ('USER');
INSERT INTO imo.role (name) VALUES ('ADMIN');

-- USER
INSERT INTO imo.user (username, password) VALUES ('user1', '$2a$10$wGQsgrdIChNQ8R5Rjd9/CuTgAdq1Okz77UURAiTa/ZLpnnmU/RmNu');
INSERT INTO imo.user (username, password) VALUES ('user2', '$2a$10$wGQsgrdIChNQ8R5Rjd9/CuTgAdq1Okz77UURAiTa/ZLpnnmU/RmNu');

-- USER_ROLE
INSERT INTO imo.user_role (id_user, id_role) VALUES (1, 1);
INSERT INTO imo.user_role (id_user, id_role) VALUES (1, 2);
INSERT INTO imo.user_role (id_user, id_role) VALUES (2, 2);