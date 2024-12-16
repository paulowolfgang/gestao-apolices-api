CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          senha VARCHAR(255) NOT NULL,
                          papel ENUM('SUPER_ADMIN', 'CORRETOR') NOT NULL,
                          data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE clientes (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          cpf VARCHAR(14) UNIQUE NOT NULL,
                          email VARCHAR(100) NOT NULL,
                          endereco VARCHAR(255),
                          telefone VARCHAR(20),
                          tipo ENUM('FISICO', 'JURIDICO') NOT NULL,
                          usuario_id BIGINT NOT NULL,
                          data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE SET NULL
);

CREATE TABLE apolices (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          numero VARCHAR(50) UNIQUE NOT NULL,
                          cliente_id BIGINT NOT NULL,
                          tipo ENUM('VEICULO', 'RESIDENCIA', 'VIDA', 'SAUDE') NOT NULL,
                          valor_cobertura DECIMAL(15, 2) NOT NULL,
                          premio_mensal DECIMAL(15, 2) NOT NULL,
                          premio_total DECIMAL(15, 2) NOT NULL,
                          parcelas_totais INT NOT NULL,
                          parcelas_pagas INT DEFAULT 0,
                          data_inicio DATE NOT NULL,
                          data_fim DATE NOT NULL,
                          status ENUM('ATIVA', 'CANCELADA', 'SUSPENSA') DEFAULT 'ATIVA',
                          FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);

CREATE TABLE pagamentos (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            apolice_id BIGINT NOT NULL,
                            valor DECIMAL(15, 2) NOT NULL,
                            data_vencimento DATE NOT NULL,
                            data_pagamento DATE,
                            status ENUM('PAGO', 'ATRASADO') DEFAULT 'ATRASADO',
                            FOREIGN KEY (apolice_id) REFERENCES apolices(id) ON DELETE CASCADE
);

CREATE TABLE sinistros (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           numero_sinistro VARCHAR(50) UNIQUE NOT NULL,
                           apolice_id BIGINT NOT NULL,
                           descricao TEXT NOT NULL,
                           data_ocorrido DATE NOT NULL,
                           valor_estimado DECIMAL(15, 2) NOT NULL,
                           status ENUM('EM_ANALISE', 'APROVADO', 'REJEITADO') DEFAULT 'EM_ANALISE',
                           FOREIGN KEY (apolice_id) REFERENCES apolices(id) ON DELETE CASCADE
);
