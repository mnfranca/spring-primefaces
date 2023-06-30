-- PERMISSAO
INSERT INTO imo.permissao (nome) VALUES ('ADMIN');
INSERT INTO imo.permissao (nome) VALUES ('USUARIO');
INSERT INTO imo.permissao (nome) VALUES ('SERVIDOR');
INSERT INTO imo.permissao (nome) VALUES ('AUTORIDADE');
INSERT INTO imo.permissao (nome) VALUES ('ESTAGIARIO');
INSERT INTO imo.permissao (nome) VALUES ('GESTOR_PAGAMENTO');
INSERT INTO imo.permissao (nome) VALUES ('GESTOR_FREQUENCIA');
INSERT INTO imo.permissao (nome) VALUES ('GESTOR_BENEFICIOS');

-- USUARIO
INSERT INTO imo.usuario (login, matricula, senha) VALUES ('user1', '00000001', '$2a$10$wGQsgrdIChNQ8R5Rjd9/CuTgAdq1Okz77UURAiTa/ZLpnnmU/RmNu');
INSERT INTO imo.usuario (login, matricula, senha) VALUES ('user2', '00000002', '$2a$10$wGQsgrdIChNQ8R5Rjd9/CuTgAdq1Okz77UURAiTa/ZLpnnmU/RmNu');
INSERT INTO imo.usuario (login, matricula, senha) VALUES ('user3', '00000003', '$2a$10$wGQsgrdIChNQ8R5Rjd9/CuTgAdq1Okz77UURAiTa/ZLpnnmU/RmNu');

-- USUARIO_PERMISSAO
INSERT INTO imo.usuario_permissao (id_usuario, id_permissao) VALUES (1, 1);
INSERT INTO imo.usuario_permissao (id_usuario, id_permissao) VALUES (1, 2);
INSERT INTO imo.usuario_permissao (id_usuario, id_permissao) VALUES (2, 2);
INSERT INTO imo.usuario_permissao (id_usuario, id_permissao) VALUES (3, 2);
