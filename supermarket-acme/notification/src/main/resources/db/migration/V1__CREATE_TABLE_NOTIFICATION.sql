CREATE TABLE tb_notification
(
    id             serial PRIMARY KEY,
    send_at        TIMESTAMP,
    sender         VARCHAR(100),
    customer_email VARCHAR(200),
    message        VARCHAR(200),
    id_customer    INTEGER NOT NULL
);