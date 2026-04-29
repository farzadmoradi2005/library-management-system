package org.example.library_management_system.service;

import org.example.library_management_system.dto.LoanRequestDTO;
import org.example.library_management_system.dto.LoanResponseDTO;
import org.example.library_management_system.model.Loan;

import java.util.List;

public interface LoanService {
    public LoanResponseDTO borrowBook(LoanRequestDTO request);
    public LoanResponseDTO returnBook(Long loanId);
    public List<LoanResponseDTO> getUserLoans(Long userId);
}
