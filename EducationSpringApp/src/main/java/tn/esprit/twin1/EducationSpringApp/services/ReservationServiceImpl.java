package tn.esprit.twin1.EducationSpringApp.services;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.dto.ReservationDto;
import tn.esprit.twin1.EducationSpringApp.dto.UserReservationRequest;
import tn.esprit.twin1.EducationSpringApp.entities.*;
import tn.esprit.twin1.EducationSpringApp.repositories.ChambreRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.ReservationRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.UserRepository;
import tn.esprit.twin1.EducationSpringApp.services.impl.JWTServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ChambreRepositorie chambreRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepositorie reservationRepositorie;

    public void addReservation(ReservationDto reservationDto) {

        Reservation reservation=new Reservation();
        reservation.setAnneeUniversaire(reservationDto.getAnneeUniversitaire());

        Chambre chambre = chambreRepository.findById(reservationDto.getChambreId())
                .orElseThrow(() -> new IllegalArgumentException("Chambre with idChambre not found"));
        int maxSimpleLimit = 1; // Adjust the limit according to your requirements
        int maxDoubleLimit = 2; // Adjust the limit according to your requirements
        int maxTripleLimit = 3; // Adjust the limit according to your requirements

        long countOfSimple = countReservationsByType(chambre, TypeChambre.SIMPLE);
        long countOfDouble = countReservationsByType(chambre, TypeChambre.DOUBLE);
        long countOfTriple = countReservationsByType(chambre, TypeChambre.TRIPLE);
        if (chambre.getTypeChambre() == TypeChambre.SIMPLE && countOfSimple >= maxSimpleLimit) {
            throw new IllegalArgumentException("Maximum limit of typeChambre.Simple reached for this Chambre");
        }

        if (chambre.getTypeChambre() == TypeChambre.DOUBLE && countOfDouble >= maxDoubleLimit) {
            throw new IllegalArgumentException("Maximum limit of typeChambre.Double reached for this Chambre");
        }

        if (chambre.getTypeChambre() == TypeChambre.TRIPLE && countOfTriple >= maxTripleLimit) {
            throw new IllegalArgumentException("Maximum limit of typeChambre.Triple reached for this Chambre");
        }
        reservation.setChambre(chambre);


        User user = userRepository.findById(reservationDto.getUserId())


               .orElseThrow(() -> new IllegalArgumentException("User with userId not found"));
        System.out.println("----------ADD Reservation"+user);



        reservation.setUser(user);



       reservationRepositorie.save(reservation);
    }

    @Override
    public List<ReservationDto> findAllReservations() {

        List<Reservation> reservations = reservationRepositorie.findAll();
        return reservations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ReservationDto convertToDto(Reservation reservation) {
        ReservationDto dto=new ReservationDto();
        dto.setAnneeUniversitaire(reservation.getAnneeUniversaire());
        dto.setId(reservation.getIdReservation());
        if (reservation.getChambre() !=null)
        {
            Chambre chambre = chambreRepository.findById(reservation.getChambre().getIdChambre()).orElse(null);
            if (chambre != null) {
                dto.setChambreId(chambre.getIdChambre());
                dto.setNumeroChambre(chambre.getNumeroChambre());
                dto.setTypeChambre(chambre.getTypeChambre().name());
            }
        }
        if(reservation.getUser()!=null)
        {
            User user = userRepository.findById(reservation.getUser().getId()).orElse(null);
            if (user != null) {
                dto.setUserId(user.getId());
                dto.setUserEmail(user.getEmail());
            }
        }
        return dto;
    }

    @Override
    public ReservationDto findReservationById(long idReservation) {
       // return reservationRepositorie.findById(idReservation).isPresent() ? reservationRepositorie.findById(idReservation).get() : null;
        Optional<Reservation> optionalReservation = reservationRepositorie.findById(idReservation);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            return convertToDto(reservation); // Use the existing conversion method
        }
        return null; // Or throw an exception or handle the case where the reservation is not found
    }

    @Override
    public void Delete(Long id) {
        reservationRepositorie.deleteById(id);
    }

    @Override
    public List<Chambre> getNotReservedRooms() {
        return reservationRepositorie.findNotReservedRooms();
    }

    @Override
    public Reservation updateReservation(Long idReservation,ReservationDto reservationDto) {
        Reservation reservation = reservationRepositorie.findById(idReservation)
                .orElseThrow(() -> new IllegalArgumentException("Reservation with id " + idReservation + " not found"));

        // Validate or modify other fields in reservation as needed
        //reservation.setAnneeUniversaire(reservationDto.getAnneeUniversitaire());

        Chambre chambre = reservation.getChambre();
        if (chambre != null) {
            chambre.setNumeroChambre(reservationDto.getNumeroChambre());
            TypeChambre typeChambre = TypeChambre.valueOf(reservationDto.getTypeChambre());
            chambre.setTypeChambre(typeChambre);

            reservationRepositorie.save(reservation);
        } else {
            throw new IllegalArgumentException("Chambre not found for reservation with id " + idReservation);
        }

        return reservation;
    }

    @Override
    public List<User> findUsersWithoutReservation() {
        return reservationRepositorie.findUsersWithoutReservation();
    }


    private long countReservationsByType(Chambre chambre,TypeChambre typeChambre) {
        return reservationRepositorie.countByChambreTypeAndTypeChambre(typeChambre);
    }

}
