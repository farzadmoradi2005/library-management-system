package org.example.library_management_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseDTO {
    private Long id;
    private String title;
    private Integer availableAmount;
    private boolean isAvailable;
}