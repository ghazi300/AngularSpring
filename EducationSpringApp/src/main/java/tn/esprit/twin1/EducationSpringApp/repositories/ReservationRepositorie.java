package tn.esprit.twin1.EducationSpringApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.twin1.EducationSpringApp.dto.UserReservationRequest;
import tn.esprit.twin1.EducationSpringApp.entities.*;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReservationRepositorie  extends JpaRepository<Reservation,Long> {
    @Query("SELECT COUNT(r) FROM Reservation r JOIN r.chambre c WHERE c.typeChambre = :typeChambre")
    long countByChambreTypeAndTypeChambre(TypeChambre typeChambre);
    @Query("SELECT c FROM Chambre c " +
            "WHERE (c.idChambre NOT IN (SELECT r.chambre.idChambre FROM Reservation r) " +
            "OR (c.typeChambre = 'Double' AND (SELECT COUNT(r) FROM Reservation r WHERE r.chambre.idChambre = c.idChambre) < 2) " +
            "OR (c.typeChambre = 'Triple' AND (SELECT COUNT(r) FROM Reservation r WHERE r.chambre.idChambre = c.idChambre) < 3))")

    List<Chambre> findNotReservedRooms();

    @Query("SELECT e FROM User e WHERE e.role = 0 AND NOT EXISTS (SELECT 1 FROM Reservation r WHERE r.user = e)")
    List<User> findUsersWithoutReservation();

}
