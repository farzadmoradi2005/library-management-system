package org.example.library_management_system.repository;


import org.example.library_management_system.model.Loan;
import org.example.library_management_system.model.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
Page<Loan> findByUserId(Long userId , Pageable pageable);
    Page<Loan> findByBookIdAndStatus(Long bookId, LoanStatus status , Pageable pageable);
    Optional<Loan> findByUserIdAndBookIdAndStatus(Long userId,Long bookId, LoanStatus status);
    @Query("SELECT l FROM Loan l WHERE l.book.id = :bookId AND l.status = 'ACTIVE'")
    Page<Loan> findActiveLoansByBook(@Param("bookId") Long bookId, Pageable pageable);
}