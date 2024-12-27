-- Altera a coluna 'nome' para permitir valores nulos
ALTER TABLE clientes
    MODIFY COLUMN nome VARCHAR(100) NULL;

-- Altera a coluna 'cpf' para permitir valores nulos
ALTER TABLE clientes
    MODIFY COLUMN cpf VARCHAR(14) NULL;

-- Altera a coluna 'data_nascimento' para permitir valores nulos
ALTER TABLE clientes
    MODIFY COLUMN data_nascimento DATE NULL;