package ru.spring.auto.rest.docs.demo.controllers

import org.junit.Test
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import ru.ryabov.swagger_library.rest.model.Category
import ru.ryabov.swagger_library.rest.model.Pet

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class PetControllerAutoTest extends MockMvcDocumentSpec {

    @Test
    void addPetTest() {
        mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/pet")
                        .content(objectMapper.writeValueAsString(buildPet()))
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
        ).andExpect(status().isOk())
    }

    @Test
    void getPetTest() {
        //given
        def petId = 1L
        //when
        def resultActions = mockMvc
                .perform(RestDocumentationRequestBuilders
                        .get("/pet/{petId}", petId)
                        .header("Accept", "application/json"))
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(buildReturnPet())))
    }

    @Test
    void deletePetTest() {
        def petId = 200L
        mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/pet")
                        .content(objectMapper.writeValueAsString(buildPet(petId)))
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json"))

        mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete("/pet/{petId}", petId)
                        .header("Accept", "application/json")
        ).andExpect(status().isOk())
    }

    @Test
    void deletePetNegativeTest() {
        def petId = 100L
        mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete("/pet/{petId}", petId)
                        .header("Accept", "application/json")
        ).andExpect(status().isNotFound())
    }

    private static Pet buildPet() {
        Pet pet = new Pet()
        pet.setId(2L)
        pet.setName("Pussy")
        Category category = new Category()
        category.setId(2L)
        category.setName("Cat")
        pet.setCategory(category)
        pet.setStatus(Pet.StatusEnum.AVAILABLE)
        return pet
    }

    private static Pet buildPet(Long id) {
        Pet pet = new Pet()
        pet.setId(id)
        pet.setName("Pussy")
        Category category = new Category()
        category.setId(2L)
        category.setName("Cat")
        pet.setCategory(category)
        pet.setStatus(Pet.StatusEnum.AVAILABLE)
        return pet
    }

    private static Pet buildReturnPet() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Fluffy");
        Category category = new Category();
        category.setId(1L);
        category.setName("Dog");
        pet.setCategory(category);
        pet.setStatus(Pet.StatusEnum.AVAILABLE);
        return pet
    }
}
