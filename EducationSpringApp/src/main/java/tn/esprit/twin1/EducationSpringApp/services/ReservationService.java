package tn.esprit.twin1.EducationSpringApp.services;

import tn.esprit.twin1.EducationSpringApp.dto.ReservationDto;
import tn.esprit.twin1.EducationSpringApp.dto.UserReservationRequest;
import tn.esprit.twin1.EducationSpringApp.entities.*;

import java.util.List;

public interface ReservationService {
    void addReservation(ReservationDto reservationDTO);

    List<ReservationDto> findAllReservations();

    ReservationDto findReservationById(long idReservation);

    void Delete(Long id);

    List<Chambre> getNotReservedRooms();

    Reservation updateReservation(Long idReservation, ReservationDto reservationDto);

    List<User> findUsersWithoutReservation();
}
