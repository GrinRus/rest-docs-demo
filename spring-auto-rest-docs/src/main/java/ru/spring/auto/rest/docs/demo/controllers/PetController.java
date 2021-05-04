package ru.spring.auto.rest.docs.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ryabov.swagger_library.rest.api.PetApi;
import ru.ryabov.swagger_library.rest.model.Pet;
import ru.spring.auto.rest.docs.demo.repository.PetRepository;

import javax.validation.Valid;

/**
 * Controller that implements methods PetApi
 */
@RestController
@RequiredArgsConstructor
public class PetController implements PetApi {

    private final PetRepository petRepository;

    /**
     * Method addPet
     * @param body pet
     * @return true or false
     */
    @Override
    public ResponseEntity<Boolean> addPet(@Valid Pet body) {
        return new ResponseEntity<>(petRepository.addPet(body), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deletePet(Long petId, String apiKey) {
        return new ResponseEntity<>(petRepository.deletePet(petId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        return new ResponseEntity<>(petRepository.getPetById(petId), HttpStatus.OK);
    }
}
