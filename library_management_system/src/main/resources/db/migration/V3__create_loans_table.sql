
CREATE TABLE loans (
                       id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id     BIGINT NOT NULL,
                       book_id     BIGINT NOT NULL,
                       status      ENUM('ACTIVE', 'RETURNED', 'OVERDUE') NOT NULL DEFAULT 'ACTIVE',
                       return_date DATE,
                       loan_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_loan_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                       CONSTRAINT fk_loan_book FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);