CREATE TABLE tb_fraud
(
    id           serial PRIMARY KEY,
    description  VARCHAR(200) NOT NULL,
    customer_cpf VARCHAR(20)  NOT NULL,
    is_fraud     BOOLEAN      NOT NULL,
    create_at    TIMESTAMP
);