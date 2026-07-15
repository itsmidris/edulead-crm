package com.itsmidris.edulead_crm.common.payload;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private boolean success;
    private String message;
    private int status;
    private List<String> errors;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
