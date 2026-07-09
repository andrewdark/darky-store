package ua.pp.darknsoft.darkystore.service;

import jakarta.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ua.pp.darknsoft.darkystore.dto.RegisterRequestDto;

public interface IAuthService {
    void checkRegistrationData(RegisterRequestDto registerRequestDto) throws MethodArgumentNotValidException;

    void register( RegisterRequestDto registerRequestDto);
}
