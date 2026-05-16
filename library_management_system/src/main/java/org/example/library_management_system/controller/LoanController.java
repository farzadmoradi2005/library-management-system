package org.example.library_management_system.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.library_management_system.dto.LoanRequestDTO;
import org.example.library_management_system.dto.LoanResponseDTO;
import org.example.library_management_system.dto.PageResponseDTO;
import org.example.library_management_system.service.LoanService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponseDTO> borrowBook(@Valid @RequestBody LoanRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanService.borrowBook(request));
    }

    @PutMapping("/{loanId}/return")
    public ResponseEntity<LoanResponseDTO> returnBook(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.returnBook(loanId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PageResponseDTO<LoanResponseDTO>> getUserLoans(@PathVariable Long userId , Pageable pageable) {
        return ResponseEntity.ok(loanService.getUserLoans(userId , pageable));
    }
}