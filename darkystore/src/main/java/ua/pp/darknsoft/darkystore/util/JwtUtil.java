package ua.pp.darknsoft.darkystore.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ua.pp.darknsoft.darkystore.constants.ApplicationConstants;
import ua.pp.darknsoft.darkystore.security.AppUser;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final Environment env;

    public String generateJwtToken(Authentication authentication) {
        String jwt = "";
        String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
                ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        AppUser fetchedUser = (AppUser) authentication.getPrincipal();
        if (fetchedUser == null) {
            return null;
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", fetchedUser.getId());
        claims.put("name", fetchedUser.getName());
        claims.put("username", fetchedUser.getUsername());
        claims.put("email", fetchedUser.getEmail());
        claims.put("roles", fetchedUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray());

        jwt = Jwts.builder()
                .header().type("JWT")
                .and()
                .issuer("Darky Store").subject("JWT Token")
                .claims(claims)
                .issuedAt(new java.util.Date())
                .expiration(new java.util.Date((new java.util.Date()).getTime() + 60 * 60 * 1000))
                .signWith(secretKey).compact();
        return jwt;
    }
}
