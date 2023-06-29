-- PERMISSAO
INSERT INTO imo.permissao (nome) VALUES ('ADMIN');
INSERT INTO imo.permissao (nome) VALUES ('USUARIO');
INSERT INTO imo.permissao (nome) VALUES ('GESTOR_PAGAMENTO');
INSERT INTO imo.permissao (nome) VALUES ('GESTOR_FREQUENCIA');
INSERT INTO imo.permissao (nome) VALUES ('GESTOR_BENEFICIOS');

-- USUARIO
INSERT INTO imo.usuario (login, senha) VALUES ('user1', '$2a$10$wGQsgrdIChNQ8R5Rjd9/CuTgAdq1Okz77UURAiTa/ZLpnnmU/RmNu');
INSERT INTO imo.usuario (login, senha) VALUES ('user2', '$2a$10$wGQsgrdIChNQ8R5Rjd9/CuTgAdq1Okz77UURAiTa/ZLpnnmU/RmNu');

-- USUARIO_PERMISSAO
INSERT INTO imo.usuario_permissao (id_usuario, id_permissao) VALUES (1, 1);
INSERT INTO imo.usuario_permissao (id_usuario, id_permissao) VALUES (1, 2);
INSERT INTO imo.usuario_permissao (id_usuario, id_permissao) VALUES (2, 2);