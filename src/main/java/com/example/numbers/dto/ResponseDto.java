package com.example.numbers.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String status;
    private String message;
    private Object data;

    public ResponseDto(HttpStatus status, String message, Object data) {
        this.status = status.getReasonPhrase();
        this.message = message;
        this.data = data;
    }

    public ResponseDto(Object data) {
        this.status = HttpStatus.OK.toString();
        this.message = HttpStatus.OK.getReasonPhrase();
        this.data = data;
    }

    public String toString() {
        return "ResponseDTO(status=" + this.getStatus() + ", message=" + this.getMessage() + ", data=" + this.getData() + ")";
    }
}
