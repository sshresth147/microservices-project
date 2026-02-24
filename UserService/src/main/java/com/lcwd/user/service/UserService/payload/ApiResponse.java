package com.lcwd.user.service.UserService.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

//using lambok here now

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ApiResponse {

    private String mesage;
    private boolean success;
    private HttpStatus status;
}
