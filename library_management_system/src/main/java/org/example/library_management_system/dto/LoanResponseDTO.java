package org.example.library_management_system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.example.library_management_system.model.LoanStatus;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponseDTO {

    private long id;

    private String bookTitle;
    private long bookId;

    private String userName;

    private LoanStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    private String message;
}