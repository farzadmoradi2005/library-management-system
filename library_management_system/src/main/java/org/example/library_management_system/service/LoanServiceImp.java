package org.example.library_management_system.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.library_management_system.Mapper.BookMapper;
import org.example.library_management_system.Mapper.LoanMapper;
import org.example.library_management_system.dto.LoanRequestDTO;
import org.example.library_management_system.dto.LoanResponseDTO;
import org.example.library_management_system.exception.BookNotAvailableException;
import org.example.library_management_system.exception.ResourceNotFoundException;
import org.example.library_management_system.model.Book;
import org.example.library_management_system.model.Loan;
import org.example.library_management_system.model.LoanStatus;
import org.example.library_management_system.model.User;
import org.example.library_management_system.repository.BookRepository;
import org.example.library_management_system.repository.LoanRepository;
import org.example.library_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanServiceImp implements LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final UserRepository userRepository;
    private final LoanMapper loanMapper;


    @Override
    @Transactional
    public LoanResponseDTO borrowBook(LoanRequestDTO request) {
        Book book = bookRepository.findById(request.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(book.getAvailableAmount() <=0){
            throw new BookNotAvailableException("Book available amount is zero");
        }
        boolean alreadyBorrowed = loanRepository.findByUserIdAndBookIdAndStatus(user.getId() , book.getId() , LoanStatus.ACTIVE).isPresent();
        if(alreadyBorrowed){
            throw new BookNotAvailableException("Book already borrowed");
        }
        book.setAvailableAmount(book.getAvailableAmount() - 1);
        bookRepository.save(book);
        Loan loan = Loan.builder()
                .user(user)
                .book(book)
                .status(LoanStatus.ACTIVE)
                .returnDate(LocalDate.now().plusDays(10))
                .build();
        return loanMapper.loanToLoanResponseDTO(loanRepository.save(loan));
    }

    @Override
    public LoanResponseDTO returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        Book book = loan.getBook();
        book.setAvailableAmount(book.getAvailableAmount() + 1);
        bookRepository.save(book);
        loan.setStatus(LoanStatus.RETURNED);
        loan.setReturnDate(LocalDate.now());
        return loanMapper.loanToLoanResponseDTO(loanRepository.save(loan));
    }

    @Override
    public List<LoanResponseDTO> getUserLoans(Long userId) {
        return loanRepository.findByUserId(userId).stream().map(loanMapper::loanToLoanResponseDTO).toList();
    }
}
