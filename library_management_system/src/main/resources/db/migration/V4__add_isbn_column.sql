CREATE TABLE books (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       is_available BOOLEAN DEFAULT TRUE,
                       isbn VARCHAR(20) UNIQUE,
                       available_amount INT NOT NULL,
                       total_copies INT NOT NULL,
                       CONSTRAINT chk_total_copies CHECK (total_copies >= 1)
);