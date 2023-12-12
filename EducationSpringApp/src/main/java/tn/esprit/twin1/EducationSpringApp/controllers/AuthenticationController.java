package tn.esprit.twin1.EducationSpringApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.EducationSpringApp.dto.*;

import tn.esprit.twin1.EducationSpringApp.entities.Otp;
import tn.esprit.twin1.EducationSpringApp.entities.User;

import tn.esprit.twin1.EducationSpringApp.exception.EducationException;
import tn.esprit.twin1.EducationSpringApp.repositories.OtpRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.UserRepository;
import tn.esprit.twin1.EducationSpringApp.services.AuthenticationService;
import tn.esprit.twin1.EducationSpringApp.services.OtpService;



@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")


public class AuthenticationController {

    
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private OtpService otpService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OtpRepositorie otpRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public ResponseEntity<User>sighup(@RequestBody SignUpRequest signUpRequest)
    {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));

    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse>signin(@RequestBody LoginRequest loginRequest)
    {
        return ResponseEntity.ok(authenticationService.signIn(loginRequest));

    }
    @Transactional
    @PostMapping("/otp")
    public ResponseEntity<String> resetPassword(@RequestBody OtpRequest otpRequest) {
        try {
            System.out.println("---------------------" + otpRequest.getEmail());
            otpService.sendOtp(otpRequest.getEmail());

            return ResponseEntity.status(HttpStatus.CREATED).body("OTP sent successfully");
        } catch (EducationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resetPasswordDone")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest req) {
        try {
            User user = userRepository.findUserByEmailForOtp(req.getEmail());
            if (user == null) {
                return ResponseEntity.ok().body(new ApiResponse("User does not exist with this email!!"));
            }

            Otp otps = otpRepository.findByEmail(req.getEmail());
            if (otps == null) {
                return ResponseEntity.ok().body(new ApiResponse("Something went wrong"));
            }

            String otp =Integer.toString(otps.getOtp());
            if (!otp.equals(req.getOtp())) {
                return ResponseEntity.ok().body(new ApiResponse("Invalid Otp!!!"));
            }

            user.setPassword(passwordEncoder.encode(req.getNewPassword()));

            userRepository.save(user);

            return ResponseEntity.ok().body(new ApiResponse("Password updated!!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(new ApiResponse("Something went wrong"));
        }
    }




}
