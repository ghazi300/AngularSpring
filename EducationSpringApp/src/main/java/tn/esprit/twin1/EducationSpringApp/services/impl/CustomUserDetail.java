package tn.esprit.twin1.EducationSpringApp.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.User;
import tn.esprit.twin1.EducationSpringApp.repositories.UserRepository;

@Service

public class CustomUserDetail implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =(User) this.userRepository.findByEmail(username);
        if(user == null)
        {
            System.out.println("user not found");
            throw new UsernameNotFoundException("No user found !!");

        }
        return user;
    }


}


