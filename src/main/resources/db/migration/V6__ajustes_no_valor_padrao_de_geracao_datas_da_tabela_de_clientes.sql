-- Remover comportamento automático dos campos de data
ALTER TABLE clientes
    MODIFY COLUMN data_nascimento TIMESTAMP NULL;

ALTER TABLE clientes
    MODIFY COLUMN data_abertura TIMESTAMP NULL;
