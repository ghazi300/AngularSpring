package tn.esprit.twin1.EducationSpringApp.services;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.dto.NotificationEmail;
import tn.esprit.twin1.EducationSpringApp.entities.Otp;
import tn.esprit.twin1.EducationSpringApp.entities.User;
import tn.esprit.twin1.EducationSpringApp.exception.EducationException;
import tn.esprit.twin1.EducationSpringApp.repositories.OtpRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

import java.util.Date;
import java.util.List;
import java.util.Random;



@Service
@AllArgsConstructor

public class OtpService {
    private final UserRepository userRepository;
    private final MailService mailService;
    private final OtpRepositorie otpRepositorie;
    @Async

    public void sendOtp(String email) throws EducationException {
        User user =  userRepository.findUserByEmailForOtp(email);
        System.out.println("Email --------------------"+email);
        System.out.println("User --------------------"+user);
        if (user == null) {
            throw new EducationException("User does not exist with this email");
        }




        int otp = generateOtp();
        saveOtp(email, otp);



        mailService.sendMail(new NotificationEmail("Reset Password !!",user.getEmail(),String.valueOf(otp)));

    }

    @Scheduled(fixedDelay = 120000)
    public void deleteExpiredOtps() {
        List<Otp> otps = otpRepositorie.findAll();
        LocalDateTime now = LocalDateTime.now();
        for (Otp otp : otps) {
            LocalDateTime expirationTime = otp.getCreatedAt().plusMinutes(2); // Adjust as needed
            if (expirationTime.isBefore(now)) {
                otpRepositorie.delete(otp);
            }
        }
    }




    private void saveOtp(String email, int otp) {
        Otp otp1=new Otp();
        otp1.setEmail(email);
        otp1.setOtp(otp);
        otp1.setCreatedAt(LocalDateTime.now());
        otpRepositorie.save(otp1);
    }
     @Transactional
     void  deleteOtp(String email) {
      otpRepositorie.deleteByEmail(email);

    }

    private int generateOtp() {
        return 1000 + new Random().nextInt(9000);
    }



}
