-- Tabela CLIENTE
CREATE TABLE cliente (
    id_cliente      INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome            VARCHAR(100) NOT NULL,
    email           VARCHAR(100),
    telefone        VARCHAR(20)
);

-- Tabela RESPONSÁVEL
CREATE TABLE responsavel (
    id_responsavel  INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome            VARCHAR(100) NOT NULL,
    cargo           VARCHAR(50)
);

-- Tabela ORDEM DE SERVIÇO
CREATE TABLE ordem_servico (
    id_ordem_servico   INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    data_abertura      DATE NOT NULL,
    data_fechamento    DATE,
    status             VARCHAR(20)   -- ex.: 'SOLICITADO','ANALISE','EXECUCAO','ARQUIVADO'
);

-- Tabela PEDIDO
-- Cada PEDIDO é gerado por 1 CLIENTE e está ligado a 1 ORDEM_DE_SERVIÇO
CREATE TABLE pedido (
    id_pedido        INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_cliente       INTEGER NOT NULL,
    id_ordem_servico INTEGER NOT NULL,
    data_pedido      DATE NOT NULL,
    descricao        TEXT,

    CONSTRAINT fk_pedido_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente),

    CONSTRAINT fk_pedido_ordem_servico
        FOREIGN KEY (id_ordem_servico)
        REFERENCES ordem_servico (id_ordem_servico)
);

-- Relação N:N entre PEDIDO e RESPONSÁVEL (Possui)
CREATE TABLE pedido_responsavel (
    id_pedido       INTEGER NOT NULL,
    id_responsavel  INTEGER NOT NULL,
    data_atribuicao DATE,
    PRIMARY KEY (id_pedido, id_responsavel),

    CONSTRAINT fk_pr_pedido
        FOREIGN KEY (id_pedido)
        REFERENCES pedido (id_pedido),

    CONSTRAINT fk_pr_responsavel
        FOREIGN KEY (id_responsavel)
        REFERENCES responsavel (id_responsavel)
);
