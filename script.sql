DROP TABLE T_SL_VEICULO CASCADE CONSTRAINTS;

CREATE TABLE T_SL_VEICULO (
    placa VARCHAR2(7) PRIMARY KEY,
    modelo VARCHAR2(50) NOT NULL,
    marca VARCHAR2(50) NOT NULL,
    ano NUMBER(4) NOT NULL,
    quilometragem NUMBER NOT NULL,
    diagnostico VARCHAR2(255),
    sintomas VARCHAR2(255),
    tipo_problema VARCHAR2(255),
    custo_estimado NUMBER
);