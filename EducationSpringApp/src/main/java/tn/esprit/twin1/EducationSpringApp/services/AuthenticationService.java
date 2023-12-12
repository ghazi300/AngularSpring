package tn.esprit.twin1.EducationSpringApp.services;


import tn.esprit.twin1.EducationSpringApp.dto.JwtAuthenticationResponse;
import tn.esprit.twin1.EducationSpringApp.dto.LoginRequest;
import tn.esprit.twin1.EducationSpringApp.dto.SignUpRequest;
import tn.esprit.twin1.EducationSpringApp.entities.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(LoginRequest loginRequest);
}
