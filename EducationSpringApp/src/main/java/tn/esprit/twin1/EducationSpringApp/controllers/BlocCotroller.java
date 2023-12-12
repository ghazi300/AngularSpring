package tn.esprit.twin1.EducationSpringApp.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.EducationSpringApp.entities.*;
import tn.esprit.twin1.EducationSpringApp.repositories.BlocRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.FoyerRepositorie;
import tn.esprit.twin1.EducationSpringApp.services.BlocService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/bloc")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class BlocCotroller {
    private final BlocService blocService;
    private final BlocRepositorie blocRepositorie;


    @GetMapping("/getAll")
    public ResponseEntity<List<Bloc>> getAllEtudiant() {
        List<Bloc> etudiants = blocService.findAllBloc();
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    @PostMapping("/new")
    public Bloc addBloc(@RequestBody Bloc bloc) {

        return blocService.addBloc(bloc);
    }

    @PutMapping("/update/{idBloc}")
    public Bloc updateFoyer(@PathVariable long idBloc,@RequestBody AddBlocRequest bloc) {
        return blocService.updateBloc(idBloc,bloc);
    }

    @GetMapping("/getId/{idBloc}")
    public Bloc getId(@PathVariable long idBloc) {
        return blocService.findBlocById(idBloc);
    }


    @DeleteMapping("/delete/{idBloc}")
    public ResponseEntity<String> deleteFoyer(@PathVariable long idBloc) {
        try {
            Bloc bloc = blocService.findBlocById(idBloc);
            if (bloc != null) {
                blocService.deleteBlocById(idBloc);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions with a 500 Internal Server Error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/foyers/addBloc")
    public ResponseEntity<String> addBlocToFoyer(@RequestBody AddBlocRequest request) {


       blocService.addBlocToFoyer(request);
        return ResponseEntity.ok("Bloc added to Foyer successfully");
    }




        // ...

        @GetMapping("/blocs")
        public List<BlocDto> getAllBlocs() {
            List<Bloc> blocs = blocRepositorie.findAll();
            List<BlocDto> blocDtos = new ArrayList<>();

            for (Bloc bloc : blocs) {
                BlocDto blocDto = new BlocDto();
                blocDto.setIdBloc(bloc.getIdBloc());
                blocDto.setNomBloc(bloc.getNomBloc());
                blocDto.setCapaciteBloc(bloc.getCapaciteBloc());

                blocDto.setNomFoyer(bloc.getFoyer().getNomFoyer()); // Assuming Foyer has a 'name' property

                blocDtos.add(blocDto);
            }

            return blocDtos;
        }


    @Autowired
    private FoyerRepositorie foyerRepository;

    @GetMapping("/foyernames")
    public List<String> getFoyerNames() {
        List<Foyer> foyers = foyerRepository.findAll();
        return foyers.stream()
                .map(Foyer::getNomFoyer)
                .collect(Collectors.toList());
    }
    @GetMapping("/getChambres/{nomBloc}")
    public Set<Chambre> getByNomBloc(@PathVariable String nomBloc) {
        return blocRepositorie.getByNomBloc(nomBloc);
    }




}


