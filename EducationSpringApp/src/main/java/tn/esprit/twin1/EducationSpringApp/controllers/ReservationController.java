package tn.esprit.twin1.EducationSpringApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.EducationSpringApp.dto.ReservationDto;
import tn.esprit.twin1.EducationSpringApp.dto.UserReservationRequest;
import tn.esprit.twin1.EducationSpringApp.entities.Chambre;
import tn.esprit.twin1.EducationSpringApp.entities.Etudiant;
import tn.esprit.twin1.EducationSpringApp.entities.Reservation;
import tn.esprit.twin1.EducationSpringApp.entities.User;
import tn.esprit.twin1.EducationSpringApp.services.ChambreServiceImpl;
import tn.esprit.twin1.EducationSpringApp.services.ReservationService;
import tn.esprit.twin1.EducationSpringApp.services.ReservationServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
@RestController
@CrossOrigin("http://localhost:4200")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> addReservation(@RequestBody ReservationDto reservationDTO) {
        try {
            reservationService.addReservation(reservationDTO);
            return new ResponseEntity<>(reservationDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        try {
            List<ReservationDto> reservations = reservationService.findAllReservations();
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getBy/{idReservation}")
    public ReservationDto getId(@PathVariable long idReservation) {
        return reservationService.findReservationById(idReservation);
    }

    @DeleteMapping("/delete/{idReservation}")
    public ResponseEntity<?> Delete(@PathVariable("idReservation") Long id) {
        reservationService.Delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/not-reserved")
    public List<Chambre> getNotReservedRooms() {
        return reservationService.getNotReservedRooms();
    }
    @PutMapping("/update/{idReservation}")
    public ResponseEntity<?> updateChambreInfo(@PathVariable Long idReservation, @RequestBody ReservationDto reservationDto) {
        try {
            reservationService.updateReservation(idReservation, reservationDto);
            return new ResponseEntity<>("Chambre information updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/students-without-reservation")
    public ResponseEntity<List<UserReservationRequest>> getEtudiantsWithoutReservation() {
        List<User> usersWithoutReservation = reservationService.findUsersWithoutReservation();
        List<UserReservationRequest> userReservationRequests = mapUsersToUserReservationRequests(usersWithoutReservation);
        return new ResponseEntity<>(userReservationRequests, HttpStatus.OK);
    }

    private List<UserReservationRequest> mapUsersToUserReservationRequests(List<User> usersWithoutReservation) {
        List<UserReservationRequest> userReservationRequests = new ArrayList<>();

        for (User user : usersWithoutReservation) {
            UserReservationRequest userReservationRequest = new UserReservationRequest();
            userReservationRequest.setId(user.getId()); // Assuming User has an 'id' field
            userReservationRequest.setEmail(user.getEmail()); // Assuming User has an 'email' field
            userReservationRequest.setUsername(user.getFirstName()); // Assuming User has a 'username' field


            userReservationRequests.add(userReservationRequest);
        }

        return userReservationRequests;
    }

}

