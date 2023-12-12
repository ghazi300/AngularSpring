package tn.esprit.twin1.EducationSpringApp.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.dto.JwtAuthenticationResponse;
import tn.esprit.twin1.EducationSpringApp.dto.LoginRequest;
import tn.esprit.twin1.EducationSpringApp.dto.SignUpRequest;
import tn.esprit.twin1.EducationSpringApp.entities.Role;
import tn.esprit.twin1.EducationSpringApp.entities.User;
import tn.esprit.twin1.EducationSpringApp.repositories.UserRepository;
import tn.esprit.twin1.EducationSpringApp.services.AuthenticationService;
import tn.esprit.twin1.EducationSpringApp.services.JWTService;

import java.util.HashMap;

@Service
@RequiredArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //validate email and pasword
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    public User signup(SignUpRequest signUpRequest)
    {
        User user=new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }
    public JwtAuthenticationResponse signIn(LoginRequest loginRequest)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

        var user=userRepository.findByEmail(loginRequest.getEmail());
        var jwt=jwtService.generateToken(user);
        var refresh=jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refresh);
        return jwtAuthenticationResponse;

    }

}
