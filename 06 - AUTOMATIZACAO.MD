CREATE VIEW v_empregados_por_departamento_localidade AS
SELECT
    d.Dname           AS departamento,
    dl.Dlocation      AS localidade,
    COUNT(e.Ssn)      AS qtde_empregados
FROM department d
JOIN dept_locations dl
      ON dl.Dnumber = d.Dnumber
LEFT JOIN employee e
      ON e.Dno = d.Dnumber
GROUP BY d.Dname, dl.Dlocation
ORDER BY d.Dname, dl.Dlocation;


CREATE VIEW v_departamentos_e_gerentes AS
SELECT
    d.Dnumber,
    d.Dname           AS departamento,
    e.Ssn             AS gerente_ssn,
    CONCAT(e.Fname, ' ', e.Minit, ' ', e.Lname) AS gerente
FROM department d
LEFT JOIN employee e
       ON e.Ssn = d.Mgr_ssn
ORDER BY d.Dnumber;


CREATE VIEW v_empregados_por_projeto AS
SELECT
    p.Pnumber,
    p.Pname,
    p.Plocation,
    COUNT(DISTINCT w.Essn) AS qtde_empregados
FROM project p
LEFT JOIN works_on w
       ON p.Pnumber = w.Pno
GROUP BY p.Pnumber, p.Pname, p.Plocation
ORDER BY qtde_empregados DESC;


CREATE VIEW v_projetos_departamentos_gerentes AS
SELECT
    p.Pnumber,
    p.Pname        AS projeto,
    p.Plocation    AS local_projeto,
    d.Dnumber,
    d.Dname        AS departamento,
    CONCAT(e.Fname, ' ', e.Minit, ' ', e.Lname) AS gerente
FROM project p
JOIN department d
      ON p.Dnum = d.Dnumber
LEFT JOIN employee e
      ON d.Mgr_ssn = e.Ssn
ORDER BY p.Pnumber;


CREATE VIEW v_empregados_com_dependentes_e_se_sao_gerentes AS
SELECT DISTINCT
    e.Ssn,
    CONCAT(e.Fname, ' ', e.Minit, ' ', e.Lname) AS empregado,
    CASE
        WHEN d.Dnumber IS NOT NULL THEN 'SIM'
        ELSE 'NAO'
    END AS eh_gerente
FROM employee e
JOIN dependent dep
      ON dep.Essn = e.Ssn
LEFT JOIN department d
      ON d.Mgr_ssn = e.Ssn
ORDER BY empregado;


-- Junções e filtros principais

CREATE INDEX idx_employee_dno
    ON employee (Dno);

CREATE INDEX idx_employee_ssn
    ON employee (Ssn);

CREATE INDEX idx_department_dnumber
    ON department (Dnumber);

CREATE INDEX idx_department_mgr_ssn
    ON department (Mgr_ssn);

CREATE INDEX idx_dept_locations_dnumber
    ON dept_locations (Dnumber);

CREATE INDEX idx_project_dnum
    ON project (Dnum);

CREATE INDEX idx_project_pnumber
    ON project (Pnumber);

CREATE INDEX idx_works_on_essn
    ON works_on (Essn);

CREATE INDEX idx_works_on_pno
    ON works_on (Pno);

CREATE INDEX idx_dependent_essn
    ON dependent (Essn);
