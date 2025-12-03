# Projeto de Banco de Dados – Oficina Mecânica

Este repositório contém o projeto conceitual, lógico e físico de um banco de dados
para controle e gerenciamento de ordens de serviço em uma oficina mecânica.

O objetivo é aplicar os conceitos de modelagem de dados (modelo ER, mapeamento para
modelo relacional) e implementação em SQL, além de criar consultas que explorem
diferentes cláusulas da linguagem.

---

## 1. Contexto do Problema (Narrativa)

Sistema de controle e gerenciamento de execução de ordens de serviço em uma oficina mecânica:

- Clientes levam veículos à oficina para consertos ou revisões periódicas.
- Cada veículo é designado a uma equipe de mecânicos.
- A equipe identifica os serviços a serem executados e preenche uma Ordem de Serviço (OS) com data prevista de entrega.
- A partir da OS, calcula-se o valor dos serviços, consultando uma tabela de referência de mão de obra.
- O valor de cada peça utilizada também compõe o valor total da OS.
- O cliente autoriza a execução dos serviços.
- A mesma equipe avalia e executa os serviços.
- Mecânicos possuem código, nome, endereço e especialidade.
- Cada OS possui número, data de emissão, valor, status e data para conclusão dos trabalhos.

---

## 2. Modelo Conceitual (Descrição)

### Entidades

- **Cliente**
  - Atributos principais: código (id), nome, telefone, endereço.

- **Veículo**
  - Atributos principais: código (id), placa, modelo, ano, cor.
  - Cada veículo pertence a exatamente um cliente.

- **Mecânico**
  - Atributos principais: código (id), nome, endereço, especialidade.

- **Equipe**
  - Atributos principais: código (id), nome da equipe.
  - Uma equipe é composta por vários mecânicos.

- **Serviço**
  - Representa a tabela de referência de mão de obra.
  - Atributos: código (id), descrição, valor de mão de obra.

- **Peça**
  - Atributos: código (id), descrição, valor unitário.

- **Ordem de Serviço (OS)**
  - Atributos: número, data de emissão, data prevista de conclusão, data de conclusão, valor total, status, informação se está autorizada ou não.
  - Cada OS está associada a um veículo e a uma equipe.

### Relacionamentos

- **Cliente – Veículo**: um cliente pode possuir vários veículos (1:N).
- **Veículo – Ordem de Serviço**: um veículo pode possuir várias OS (1:N).
- **Equipe – Ordem de Serviço**: uma equipe pode executar várias OS (1:N).
- **Equipe – Mecânico**: relacionamento muitos-para-muitos (N:N).
- **Ordem de Serviço – Serviço**: uma OS pode conter vários serviços e um serviço pode aparecer em várias OS (N:N), com atributos como quantidade e valores.
- **Ordem de Serviço – Peça**: uma OS pode utilizar várias peças e uma peça pode aparecer em várias OS (N:N), com atributos como quantidade e valores.

---

## 3. Modelo Lógico (Esquema Relacional)

A partir do modelo conceitual, o esquema lógico relacional foi definido da seguinte forma:

- `CLIENTE( id_cliente PK, nome, telefone, endereco )`

- `VEICULO( id_veiculo PK, id_cliente FK, placa UNIQUE, modelo, ano, cor )`  
  - FK: `id_cliente` → `CLIENTE(id_cliente)`

- `MECANICO( id_mecanico PK, nome, endereco, especialidade )`

- `EQUIPE( id_equipe PK, nome_equipe )`

- `EQUIPE_MECANICO( id_equipe FK, id_mecanico FK, PK(id_equipe, id_mecanico) )`  
  - FK1: `id_equipe`   → `EQUIPE(id_equipe)`  
  - FK2: `id_mecanico` → `MECANICO(id_mecanico)`

- `SERVICO( id_servico PK, descricao, valor_mao_obra )`

- `PECA( id_peca PK, descricao, valor_unitario )`

- `ORDEM_SERVICO( numero_os PK, id_veiculo FK, id_equipe FK,  
                  data_emissao, data_prevista_conclusao, data_conclusao,  
                  valor_total, status, autorizada )`  
  - FK1: `id_veiculo` → `VEICULO(id_veiculo)`  
  - FK2: `id_equipe`  → `EQUIPE(id_equipe)`

- `OS_SERVICO( numero_os FK, id_servico FK,  
               quantidade, valor_unitario, valor_total,  
               PK(numero_os, id_servico) )`  
  - FK1: `numero_os`  → `ORDEM_SERVICO(numero_os)`  
  - FK2: `id_servico` → `SERVICO(id_servico)`

- `OS_PECA( numero_os FK, id_peca FK,  
            quantidade, valor_unitario, valor_total,  
            PK(numero_os, id_peca) )`  
  - FK1: `numero_os` → `ORDEM_SERVICO(numero_os)`  
  - FK2: `id_peca`   → `PECA(id_peca)`

---

## 4. Implementação em SQL

A implementação física do modelo (DDL e dados de teste) está organizada em:

- `schema.sql`  
  Contém os comandos `CREATE TABLE` com as chaves primárias e estrangeiras,
  já ajustado para um SGBD relacional (por exemplo, MySQL).
- `inserts.sql` (opcional, caso tenha separado)  
  Contém os comandos `INSERT INTO` com dados fictícios para testes.

Para executar:

1. Criar o banco de dados (se não existir).
2. Executar `schema.sql` para criação das tabelas.
3. Executar os `INSERTs` para popular o banco.

---

## 5. Consultas SQL (Queries de Exemplo)

As consultas foram elaboradas para contemplar:

- Recuperações simples com `SELECT`.
- Filtros com `WHERE`.
- Expressões para atributos derivados.
- Ordenações com `ORDER BY`.
- Agrupamentos e filtros de grupo com `GROUP BY` e `HAVING`.
- Junções entre tabelas (`JOIN`) para análises mais complexas.

### Exemplos

1. **Listar todas as ordens de serviço com status e valor:**

```sql
SELECT numero_os, data_emissao, status, valor_total
FROM ordem_servico;
Buscar ordens em execução ou aguardando autorização:

SELECT numero_os, status, data_emissao, data_prevista_conclusao
FROM ordem_servico
WHERE status IN ('EM_EXECUCAO', 'AGUARDANDO_AUTORIZACAO');


Calcular valor total de cada item de peça em uma OS:

SELECT numero_os,
       id_peca,
       quantidade,
       valor_unitario,
       quantidade * valor_unitario AS total_calculado
FROM os_peca;


Ordenar as OS do maior para o menor valor:

SELECT numero_os, status, valor_total
FROM ordem_servico
ORDER BY valor_total DESC;


Total faturado por equipe (apenas equipes com total acima de 500):

SELECT e.nome_equipe,
       SUM(o.valor_total) AS total_equipes
FROM equipe e
JOIN ordem_servico o ON o.id_equipe = e.id_equipe
GROUP BY e.nome_equipe
HAVING SUM(o.valor_total) > 500
ORDER BY total_equipes DESC;


Detalhar cada OS com cliente, veículo e equipe responsável:

SELECT o.numero_os,
       c.nome   AS nome_cliente,
       v.placa,
       v.modelo,
       e.nome_equipe,
       o.status,
       o.data_emissao,
       o.valor_total
FROM ordem_servico o
JOIN veiculo v ON o.id_veiculo = v.id_veiculo
JOIN cliente c ON v.id_cliente = c.id_cliente
JOIN equipe  e ON o.id_equipe  = e.id_equipe
ORDER BY o.numero_os;


Total de mão de obra e peças por OS, comparando com o valor registrado:

SELECT o.numero_os,
       COALESCE(s.total_servicos, 0) AS total_mao_obra,
       COALESCE(p.total_pecas,   0) AS total_pecas,
       COALESCE(s.total_servicos, 0) + COALESCE(p.total_pecas, 0) AS total_calculado_itens,
       o.valor_total AS valor_total_registrado
FROM ordem_servico o
LEFT JOIN (
    SELECT numero_os, SUM(valor_total) AS total_servicos
    FROM os_servico
    GROUP BY numero_os
) s ON s.numero_os = o.numero_os
LEFT JOIN (
    SELECT numero_os, SUM(valor_total) AS total_pecas
    FROM os_peca
    GROUP BY numero_os
) p ON p.numero_os = o.numero_os
ORDER BY o.numero_os;