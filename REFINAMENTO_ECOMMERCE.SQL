-- Crie o banco se quiser separar
CREATE DATABASE IF NOT EXISTS oficina;
USE oficina;

-- =========================
-- TABELA CLIENTE
-- =========================
CREATE TABLE cliente (
    id_cliente  INT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    telefone    VARCHAR(20),
    endereco    VARCHAR(150)
);

-- =========================
-- TABELA VEICULO
-- =========================
CREATE TABLE veiculo (
    id_veiculo  INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente  INT NOT NULL,
    placa       VARCHAR(10) NOT NULL UNIQUE,
    modelo      VARCHAR(60),
    ano         INT,
    cor         VARCHAR(30),

    CONSTRAINT fk_veiculo_cliente
        FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

-- =========================
-- TABELA MECANICO
-- =========================
CREATE TABLE mecanico (
    id_mecanico INT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    endereco    VARCHAR(150),
    especialidade VARCHAR(60)
);

-- =========================
-- TABELA EQUIPE
-- =========================
CREATE TABLE equipe (
    id_equipe   INT AUTO_INCREMENT PRIMARY KEY,
    nome_equipe VARCHAR(60) NOT NULL
);

-- =========================
-- TABELA EQUIPE x MECANICO (N:N)
-- =========================
CREATE TABLE equipe_mecanico (
    id_equipe   INT NOT NULL,
    id_mecanico INT NOT NULL,
    PRIMARY KEY (id_equipe, id_mecanico),

    CONSTRAINT fk_em_equipe
        FOREIGN KEY (id_equipe) REFERENCES equipe(id_equipe),
    CONSTRAINT fk_em_mecanico
        FOREIGN KEY (id_mecanico) REFERENCES mecanico(id_mecanico)
);

-- =========================
-- TABELA SERVICO (tabela de referência de mão de obra)
-- =========================
CREATE TABLE servico (
    id_servico      INT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(100) NOT NULL,
    valor_mao_obra  DECIMAL(10,2) NOT NULL
);

-- =========================
-- TABELA PECA
-- =========================
CREATE TABLE peca (
    id_peca        INT AUTO_INCREMENT PRIMARY KEY,
    descricao      VARCHAR(100) NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL
);

-- =========================
-- TABELA ORDEM DE SERVIÇO
-- =========================
CREATE TABLE ordem_servico (
    numero_os               INT AUTO_INCREMENT PRIMARY KEY,
    id_veiculo              INT NOT NULL,
    id_equipe               INT NOT NULL,
    data_emissao            DATE NOT NULL,
    data_prevista_conclusao DATE NOT NULL,
    data_conclusao          DATE,
    valor_total             DECIMAL(10,2),
    status                  VARCHAR(30) NOT NULL,
    autorizada              TINYINT(1) DEFAULT 0,  -- 0 = não, 1 = sim

    CONSTRAINT fk_os_veiculo
        FOREIGN KEY (id_veiculo) REFERENCES veiculo(id_veiculo),
    CONSTRAINT fk_os_equipe
        FOREIGN KEY (id_equipe) REFERENCES equipe(id_equipe)
);

-- =========================
-- TABELA OS_SERVICO (itens de serviço da OS)
-- =========================
CREATE TABLE os_servico (
    numero_os      INT NOT NULL,
    id_servico     INT NOT NULL,
    quantidade     INT NOT NULL DEFAULT 1,
    valor_unitario DECIMAL(10,2) NOT NULL,
    valor_total    DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (numero_os, id_servico),

    CONSTRAINT fk_oss_os
        FOREIGN KEY (numero_os) REFERENCES ordem_servico(numero_os),
    CONSTRAINT fk_oss_servico
        FOREIGN KEY (id_servico) REFERENCES servico(id_servico)
);

-- =========================
-- TABELA OS_PECA (itens de peça da OS)
-- =========================
CREATE TABLE os_peca (
    numero_os      INT NOT NULL,
    id_peca        INT NOT NULL,
    quantidade     INT NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    valor_total    DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (numero_os, id_peca),

    CONSTRAINT fk_osp_os
        FOREIGN KEY (numero_os) REFERENCES ordem_servico(numero_os),
    CONSTRAINT fk_osp_peca
        FOREIGN KEY (id_peca) REFERENCES peca(id_peca)
);


/*QUERYS DE EXEMPLO*/

-- =========================
-- CLIENTES
-- =========================
INSERT INTO cliente (id_cliente, nome, telefone, endereco) VALUES
(1, 'Ana Silva',   '11 99999-0001', 'Rua A, 123'),
(2, 'Bruno Lima',  '11 99999-0002', 'Rua B, 456'),
(3, 'Carlos Souza','11 99999-0003', 'Rua C, 789');

-- =========================
-- VEICULOS
-- =========================
INSERT INTO veiculo (id_veiculo, id_cliente, placa, modelo, ano, cor) VALUES
(1, 1, 'AAA-1111', 'Honda Civic', 2018, 'Prata'),
(2, 1, 'BBB-2222', 'Chevrolet Onix', 2020, 'Branco'),
(3, 2, 'CCC-3333', 'Toyota Corolla', 2015, 'Preto'),
(4, 3, 'DDD-4444', 'VW Gol', 2012, 'Vermelho');

-- =========================
-- MECANICOS
-- =========================
INSERT INTO mecanico (id_mecanico, nome, endereco, especialidade) VALUES
(1, 'João Mecânico',  'Rua M1, 10', 'Motor'),
(2, 'Marcos Freios',  'Rua M2, 20', 'Freios'),
(3, 'Paulo Geral',    'Rua M3, 30', 'Geral'),
(4, 'Rita Elétrica',  'Rua M4, 40', 'Elétrica');

-- =========================
-- EQUIPES
-- =========================
INSERT INTO equipe (id_equipe, nome_equipe) VALUES
(1, 'Equipe Motor'),
(2, 'Equipe Freios');

-- =========================
-- RELAÇÃO EQUIPE x MECANICO
-- =========================
INSERT INTO equipe_mecanico (id_equipe, id_mecanico) VALUES
(1, 1),
(1, 3),
(2, 2),
(2, 4);

-- =========================
-- SERVICOS (mão de obra)
-- =========================
INSERT INTO servico (id_servico, descricao, valor_mao_obra) VALUES
(1, 'Troca de óleo',                  100.00),
(2, 'Revisão completa',               400.00),
(3, 'Alinhamento e balanceamento',    120.00),
(4, 'Troca de pastilha de freio',     200.00);

-- =========================
-- PECAS
-- =========================
INSERT INTO peca (id_peca, descricao, valor_unitario) VALUES
(1, 'Óleo 5W30',                   60.00),
(2, 'Filtro de óleo',              30.00),
(3, 'Pastilha de freio dianteira', 250.00),
(4, 'Fluido de freio',             80.00);

-- =========================
-- ORDEM DE SERVIÇO
-- =========================
INSERT INTO ordem_servico
(numero_os, id_veiculo, id_equipe, data_emissao,
 data_prevista_conclusao, data_conclusao,
 valor_total, status, autorizada)
VALUES
(1, 1, 1, '2025-06-20', '2025-06-21', '2025-06-21', 190.00, 'CONCLUIDA',              1),
(2, 3, 2, '2025-06-21', '2025-06-23', NULL,          730.00, 'EM_EXECUCAO',           1),
(3, 4, 2, '2025-06-22', '2025-06-24', NULL,          400.00, 'AGUARDANDO_AUTORIZACAO',0),
(4, 2, 1, '2025-06-25', '2025-06-26', NULL,          120.00, 'ABERTA',                1);

-- =========================
-- ITENS DE SERVICO (OS_SERVICO)
-- =========================
-- OS 1: troca de óleo
INSERT INTO os_servico (numero_os, id_servico, quantidade, valor_unitario, valor_total) VALUES
(1, 1, 1, 100.00, 100.00),

-- OS 2: revisão completa
(2, 2, 1, 400.00, 400.00),

-- OS 3: revisão completa (aguardando autorização)
(3, 2, 1, 400.00, 400.00),

-- OS 4: alinhamento
(4, 3, 1, 120.00, 120.00);

-- =========================
-- ITENS DE PECA (OS_PECA)
-- =========================
-- OS 1: óleo + filtro
INSERT INTO os_peca (numero_os, id_peca, quantidade, valor_unitario, valor_total) VALUES
(1, 1, 1, 60.00, 60.00),
(1, 2, 1, 30.00, 30.00),

-- OS 2: pastilha + fluido
(2, 3, 1, 250.00, 250.00),
(2, 4, 1, 80.00, 80.00);
