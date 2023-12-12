package tn.esprit.twin1.EducationSpringApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import tn.esprit.twin1.EducationSpringApp.entities.Role;
import tn.esprit.twin1.EducationSpringApp.entities.User;

@Repository

public interface UserRepository extends JpaRepository<User,Integer> {
    UserDetails findByEmail(String username);
    User findByRole(Role role);
    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    User findUserByEmailForOtp(@Param("email") String email);

}
