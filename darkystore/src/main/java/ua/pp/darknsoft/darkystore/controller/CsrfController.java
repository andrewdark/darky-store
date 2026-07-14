package ua.pp.darknsoft.darkystore.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.web.csrf.CsrfToken;

@RestController
@RequestMapping("/api/v1/csrf-token")
public class CsrfController {
    @GetMapping
    public CsrfToken csrfToken(HttpServletRequest request) {
        System.out.println("csrfToken");
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }
}
