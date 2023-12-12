package tn.esprit.twin1.EducationSpringApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.twin1.EducationSpringApp.entities.Otp;

import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface OtpRepositorie extends JpaRepository<Otp,Long> {
    Otp findByEmail(String email);

    void deleteByEmail(String email);
    @Query("SELECT o FROM Otp o WHERE o.email = :email AND o.createdAt < :twoMinutesAgo")
    Otp findExpiredOtps(String email, LocalDateTime twoMinutesAgo);
}
