ALTER TABLE clientes
    MODIFY COLUMN data_nascimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE clientes
    MODIFY COLUMN data_abertura TIMESTAMP DEFAULT CURRENT_TIMESTAMP;