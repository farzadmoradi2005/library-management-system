-- src/main/resources/db/migration/V2__create_users_table.sql

CREATE TABLE users (
                       id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name       VARCHAR(255)        NOT NULL,
                       email      VARCHAR(100)        NOT NULL UNIQUE,
                       password   VARCHAR(255)        NOT NULL,
                       role       ENUM('ADMIN', 'NORMAL') NOT NULL DEFAULT 'NORMAL',
                       created_at TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);