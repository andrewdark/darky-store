package ua.pp.darknsoft.darkystore.dto;

public record LoginResponseDto(String message, UserDto user, String jwtToken) {
}
