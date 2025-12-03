USE oficina;

-- 1) Filtros e ordenações por status / valor / datas das OS
CREATE INDEX idx_os_status
    ON ordem_servico (status);

CREATE INDEX idx_os_data_emissao
    ON ordem_servico (data_emissao);

CREATE INDEX idx_os_id_equipe
    ON ordem_servico (id_equipe);

CREATE INDEX idx_os_id_veiculo
    ON ordem_servico (id_veiculo);

-- 2) Joins frequentes entre VEICULO e CLIENTE
CREATE INDEX idx_veiculo_id_cliente
    ON veiculo (id_cliente);

-- 3) Joins de itens de serviço e peças com OS
CREATE INDEX idx_os_servico_numero_os
    ON os_servico (numero_os);

CREATE INDEX idx_os_peca_numero_os
    ON os_peca (numero_os);

-- 4) Joins nas tabelas de referência
CREATE INDEX idx_os_servico_id_servico
    ON os_servico (id_servico);

CREATE INDEX idx_os_peca_id_peca
    ON os_peca (id_peca);


DELIMITER $$

CREATE PROCEDURE sp_criar_ordem_servico (
    IN  p_id_veiculo INT,
    IN  p_id_equipe  INT,
    IN  p_data_prevista DATE,
    IN  p_status VARCHAR(30),
    OUT p_numero_os INT
)
BEGIN
    INSERT INTO ordem_servico (
        id_veiculo,
        id_equipe,
        data_emissao,
        data_prevista_conclusao,
        valor_total,
        status,
        autorizada
    ) VALUES (
        p_id_veiculo,
        p_id_equipe,
        CURDATE(),
        p_data_prevista,
        0.00,
        p_status,
        0
    );

    SET p_numero_os = LAST_INSERT_ID();
END$$

DELIMITER ;


DELIMITER $$

CREATE PROCEDURE sp_adicionar_servico_os (
    IN p_numero_os  INT,
    IN p_id_servico INT,
    IN p_quantidade INT
)
BEGIN
    DECLARE v_valor_unitario DECIMAL(10,2);
    DECLARE v_total_item     DECIMAL(10,2);

    -- Busca valor padrão de mão de obra
    SELECT valor_mao_obra
    INTO v_valor_unitario
    FROM servico
    WHERE id_servico = p_id_servico;

    SET v_total_item = v_valor_unitario * p_quantidade;

    -- Insere item de serviço
    INSERT INTO os_servico (
        numero_os, id_servico, quantidade, valor_unitario, valor_total
    ) VALUES (
        p_numero_os, p_id_servico, p_quantidade, v_valor_unitario, v_total_item
    );

    -- Atualiza valor_total da OS
    UPDATE ordem_servico
    SET valor_total = COALESCE(valor_total,0) + v_total_item
    WHERE numero_os = p_numero_os;
END$$

DELIMITER ;





DELIMITER $$

CREATE PROCEDURE sp_adicionar_peca_os (
    IN p_numero_os INT,
    IN p_id_peca   INT,
    IN p_quantidade INT
)
BEGIN
    DECLARE v_valor_unitario DECIMAL(10,2);
    DECLARE v_total_item     DECIMAL(10,2);

    -- Busca valor unitário da peça
    SELECT valor_unitario
    INTO v_valor_unitario
    FROM peca
    WHERE id_peca = p_id_peca;

    SET v_total_item = v_valor_unitario * p_quantidade;

    -- Insere item de peça
    INSERT INTO os_peca (
        numero_os, id_peca, quantidade, valor_unitario, valor_total
    ) VALUES (
        p_numero_os, p_id_peca, p_quantidade, v_valor_unitario, v_total_item
    );

    -- Atualiza valor_total da OS
    UPDATE ordem_servico
    SET valor_total = COALESCE(valor_total,0) + v_total_item
    WHERE numero_os = p_numero_os;
END$$

DELIMITER ;



DELIMITER $$

CREATE PROCEDURE sp_atualizar_status_os (
    IN p_numero_os INT,
    IN p_novo_status VARCHAR(30),
    IN p_data_conclusao DATE
)
BEGIN
    UPDATE ordem_servico
    SET status = p_novo_status,
        data_conclusao = p_data_conclusao
    WHERE numero_os = p_numero_os;
END$$

DELIMITER ;


