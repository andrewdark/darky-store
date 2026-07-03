package ua.pp.darknsoft.darkystore.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDTO (LocalDateTime timestamp, int errorCode, HttpStatus status, String error, String path){
}
