package tn.esprit.twin1.EducationSpringApp.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JWTService {
     String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token ,UserDetails userDetails);

    String  generateRefreshToken(HashMap<String, Object> objectObjectHashMap, UserDetails user);
}
