CREATE TABLE pessoa (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(30),
    numero VARCHAR(30),
    complemento VARCHAR(30),
    bairro VARCHAR(30),
    cep VARCHAR(30),
    cidade VARCHAR(30),
    estado VARCHAR(30),
    ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Thiago Silva', '409 Sul Al. 17', '10', null, 'Casa', '40.305-12', 'Palmas', 'TO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Marco Antônio', '507 Sul Al. 5', '110', 'Apto 1', 'Colina', '11.400-12', 'Palmas', 'TO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Larissa Costa', '305 Sul Al. 3', '23', null, 'Casa', '54.212-12', 'Palmas', 'TO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Pedro Pereira', '503 Norte Al. 13', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Palmas', 'TO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Lucas Mariano', '104 Sul Al. 22', '321', null, 'Casa', '56.400-12', 'Palmas', 'TO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Isabele Barbosa', '103 Norte Al. 5', '100', null, 'Casa', '77.400-12', 'Palmas', 'TO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Henrique Medeiros', '507 Sul Al. 24', '1120', 'Condomínio Jardim de Versailes', 'Centro', '12.400-12', 'Palmas', 'TO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Carlos Rosa', '403 Sul Al. 17', '12', null, 'Centro', '31.400-12', 'Palmas', 'TO', false);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Lucas Oliveira', '504 Norte Al. 9', '566', null, 'Casa', '38.400-00', 'Palmas', 'TO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Pedro Martins', '1204 Sul Al. 17', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Palmas', 'TO', true);