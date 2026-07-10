package ua.pp.darknsoft.darkystore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.darknsoft.darkystore.dto.LoginRequestDto;
import ua.pp.darknsoft.darkystore.dto.LoginResponseDto;
import ua.pp.darknsoft.darkystore.dto.RegisterRequestDto;
import ua.pp.darknsoft.darkystore.dto.UserDto;
import ua.pp.darknsoft.darkystore.service.IAuthService;
import ua.pp.darknsoft.darkystore.util.JwtUtil;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> apiLogin(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginRequestDto.username(),
                    loginRequestDto.password()));
            var userDto = new UserDto();
            var loggedInUser = (User) authentication.getPrincipal();
            if (Objects.isNull(loggedInUser)) {
                return buildErrorResponse(HttpStatus.UNAUTHORIZED,
                        "Invalid username or password");
            }

            userDto.setName(loggedInUser.getUsername());
            String jwtToken = jwtUtil.generateJwtToken(authentication);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(), userDto, jwtToken));
        } catch (BadCredentialsException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED,
                    "Invalid username or password");
        } catch (AuthenticationException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED,
                    "Authentication failed");
        } catch (Exception ex) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An unexpected error occurred");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) throws MethodArgumentNotValidException {
        authService.checkRegistrationData(registerRequestDto);
        authService.register(registerRequestDto);
        inMemoryUserDetailsManager
                .createUser(new User(registerRequestDto.email(), passwordEncoder.encode(registerRequestDto.password()), List.of(new SimpleGrantedAuthority("ROLE_USER"))));

        return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
    }

    private ResponseEntity<LoginResponseDto> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new LoginResponseDto(message, null, null));
    }
}
