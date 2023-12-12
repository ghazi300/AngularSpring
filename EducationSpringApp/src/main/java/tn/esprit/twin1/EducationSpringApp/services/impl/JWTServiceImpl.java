package tn.esprit.twin1.EducationSpringApp.services.impl;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.User;
import tn.esprit.twin1.EducationSpringApp.services.JWTService;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

@Service

public class JWTServiceImpl implements JWTService {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String generateToken(UserDetails userDetails) {
        if (userDetails instanceof User) {
            User user = (User) userDetails;
            List<String> userRoles = Collections.singletonList(user.getRole().name());

            return Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .claim("roles", userRoles) // Include roles in the claim
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                    .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                    .compact();
        } else {
            throw new IllegalArgumentException("Invalid user details provided");
        }
    }
//    public String generateToken(UserDetails userDetails)
//    {
//        String userRoles = ""; // Retrieve user roles as a string or CSV format
//
//        return Jwts.builder().setSubject(userDetails.getUsername())
//                .claim("roles",userRoles)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
//                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
    //extract emial fronm the jwt
    public String extractUsername(String token)
    {
        return extractClaim(token,Claims::getSubject);
    }
    private <T> T extractClaim(String token, Function<Claims,T> claimsRevolvers)
    {
        final Claims claims=extractALLClaims(token);
        return claimsRevolvers.apply(claims);
    }

    private Claims extractALLClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSiginKey() {
//        byte[] key= Decoders.BASE64.decode("yourSecretKey");
//        return Keys.hmacShaKeyFor(key);
        return SECRET_KEY;

    }
    public boolean isTokenValid(String token ,UserDetails userDetails)
    {
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));

    }

    @Override
    public String generateRefreshToken(HashMap<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+604800000))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }
}
