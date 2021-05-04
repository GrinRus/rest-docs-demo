package ru.spring.auto.rest.docs.demo.repository;

import org.springframework.stereotype.Repository;
import ru.ryabov.swagger_library.rest.model.Category;
import ru.ryabov.swagger_library.rest.model.Pet;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PetRepository {

    private List<Pet> petList;

    @PostConstruct
    public void init() {
        petList = new ArrayList<>();
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Fluffy");
        Category category = new Category();
        category.setId(1L);
        category.setName("Dog");
        pet.setCategory(category);
        pet.setStatus(Pet.StatusEnum.AVAILABLE);
        petList.add(pet);
    }

    public Pet getPetById(Long id) {
        return petList.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }

    public boolean addPet(Pet pet) {
        return petList.add(pet);
    }

    public boolean deletePet(Long id) {
        return petList.remove(getPetById(id));
    }
}
