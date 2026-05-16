package org.example.library_management_system.service;

import org.example.library_management_system.dto.LoanRequestDTO;
import org.example.library_management_system.dto.LoanResponseDTO;
import org.example.library_management_system.dto.PageResponseDTO;
import org.example.library_management_system.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoanService {
    public LoanResponseDTO borrowBook(LoanRequestDTO request);
    public LoanResponseDTO returnBook(Long loanId);
    public PageResponseDTO<LoanResponseDTO> getUserLoans(Long userId , Pageable pageable);
}
