package org.example.library_management_system.Mapper;

import org.example.library_management_system.dto.LoanResponseDTO;
import org.example.library_management_system.model.Book;
import org.example.library_management_system.model.Loan;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface LoanMapper {
    LoanResponseDTO loanToLoanResponseDTO(Loan loan);
    Loan loanResponseDTOToloan(LoanResponseDTO loanResponseDTO);
}
