package tn.esprit.twin1.EducationSpringApp.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.Otp;
import tn.esprit.twin1.EducationSpringApp.entities.User;
import tn.esprit.twin1.EducationSpringApp.exception.EducationException;
import tn.esprit.twin1.EducationSpringApp.repositories.OtpRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.UserRepository;
import tn.esprit.twin1.EducationSpringApp.services.MailService;
import tn.esprit.twin1.EducationSpringApp.services.UserService;

@Service
@RequiredArgsConstructor

public class UserServiceImp implements UserService

{
    private final UserRepository userRepository;
    private final MailService mailService;
    private final OtpRepositorie otpRepositorie;


    public UserDetailsService userDetailsService()
    {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username);
            }
        };
    }




}
