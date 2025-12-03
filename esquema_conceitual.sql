-- CLIENTE
CREATE TABLE cliente (
    id_cliente      INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome            VARCHAR(100) NOT NULL,
    telefone        VARCHAR(20),
    endereco        VARCHAR(150)
);

-- VEICULO (cada veículo pertence a um cliente)
CREATE TABLE veiculo (
    id_veiculo      INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_cliente      INTEGER NOT NULL,
    placa           VARCHAR(10) NOT NULL UNIQUE,
    modelo          VARCHAR(60),
    ano             INTEGER,
    cor             VARCHAR(30),

    CONSTRAINT fk_veiculo_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente)
);

-- MECANICO
CREATE TABLE mecanico (
    id_mecanico     INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome            VARCHAR(100) NOT NULL,
    endereco        VARCHAR(150),
    especialidade   VARCHAR(60)
);

-- EQUIPE
CREATE TABLE equipe (
    id_equipe       INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome_equipe     VARCHAR(60) NOT NULL
);

-- Relação N:N entre EQUIPE e MECANICO
CREATE TABLE equipe_mecanico (
    id_equipe       INTEGER NOT NULL,
    id_mecanico     INTEGER NOT NULL,
    PRIMARY KEY (id_equipe, id_mecanico),

    CONSTRAINT fk_em_equipe
        FOREIGN KEY (id_equipe)
        REFERENCES equipe (id_equipe),

    CONSTRAINT fk_em_mecanico
        FOREIGN KEY (id_mecanico)
        REFERENCES mecanico (id_mecanico)
);

-- Tabela de referência de mão de obra (SERVIÇOS)
CREATE TABLE servico (
    id_servico      INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descricao       VARCHAR(100) NOT NULL,
    valor_mao_obra  DECIMAL(10,2) NOT NULL
);

-- Tabela de PEÇAS
CREATE TABLE peca (
    id_peca         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descricao       VARCHAR(100) NOT NULL,
    valor_unitario  DECIMAL(10,2) NOT NULL
);

-- ORDEM DE SERVIÇO
CREATE TABLE ordem_servico (
    numero_os               INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_veiculo              INTEGER NOT NULL,
    id_equipe               INTEGER NOT NULL,
    data_emissao            DATE NOT NULL,
    data_prevista_conclusao DATE NOT NULL,
    data_conclusao          DATE,
    valor_total             DECIMAL(10,2),
    status                  VARCHAR(20) NOT NULL,
    autorizada              BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_os_veiculo
        FOREIGN KEY (id_veiculo)
        REFERENCES veiculo (id_veiculo),

    CONSTRAINT fk_os_equipe
        FOREIGN KEY (id_equipe)
        REFERENCES equipe (id_equipe)
);

-- Itens de SERVIÇO da OS (N:N entre OS e SERVICO)
CREATE TABLE os_servico (
    numero_os       INTEGER NOT NULL,
    id_servico      INTEGER NOT NULL,
    quantidade      INTEGER NOT NULL DEFAULT 1,
    valor_unitario  DECIMAL(10,2) NOT NULL,
    valor_total     DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (numero_os, id_servico),

    CONSTRAINT fk_oss_os
        FOREIGN KEY (numero_os)
        REFERENCES ordem_servico (numero_os),

    CONSTRAINT fk_oss_servico
        FOREIGN KEY (id_servico)
        REFERENCES servico (id_servico)
);

-- Itens de PEÇA da OS (N:N entre OS e PECA)
CREATE TABLE os_peca (
    numero_os       INTEGER NOT NULL,
    id_peca         INTEGER NOT NULL,
    quantidade      INTEGER NOT NULL,
    valor_unitario  DECIMAL(10,2) NOT NULL,
    valor_total     DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (numero_os, id_peca),

    CONSTRAINT fk_osp_os
        FOREIGN KEY (numero_os)
        REFERENCES ordem_servico (numero_os),

    CONSTRAINT fk_osp_peca
        FOREIGN KEY (id_peca)
        REFERENCES peca (id_peca)
);
