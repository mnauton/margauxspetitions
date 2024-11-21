package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PetitionServiceTest {

    private PetitionService petitionService;

    @BeforeEach
    void setUp() {
        // Create a new PetitionService before each test
        petitionService = new PetitionService();
    }

    @Test
    void testCreatePetition() {
        // Arrange
        String title = "Save the Forest";
        String description = "A petition to save the rainforest.";

        // Act
        petitionService.createPetition(title, description);

        // Assert
        assertEquals(1, petitionService.getAllPetitions().size(), "Petition should be added to the list.");
        Petition petition = petitionService.getAllPetitions().get(0);
        assertEquals(title, petition.getTitle(), "The title should match.");
        assertEquals(description, petition.getDescription(), "The description should match.");
    }

    @Test
    void testGetAllPetitions() {
        // Arrange
        petitionService.createPetition("Save the Ocean", "Petition to protect the oceans.");
        petitionService.createPetition("Save the Bees", "A petition to protect bees.");

        // Act
        List<Petition> petitions = petitionService.getAllPetitions();

        // Assert
        assertEquals(2, petitions.size(), "There should be two petitions.");
    }

    @Test
    void testSearchPetitionByTitle_Found() {
        // Arrange
        petitionService.createPetition("Save the Planet", "A petition to save the Earth.");

        // Act
        Petition petition = petitionService.searchPetitionByTitle("Save the Planet");

        // Assert
        assertNotNull(petition, "Petition should be found.");
        assertEquals("Save the Planet", petition.getTitle(), "The title should match.");
    }

    @Test
    void testSearchPetitionByTitle_NotFound() {
        // Arrange
        petitionService.createPetition("Save the Planet", "A petition to save the Earth.");

        // Act
        Petition petition = petitionService.searchPetitionByTitle("Save the Forest");

        // Assert
        assertNull(petition, "Petition should not be found.");
    }

    @Test
    void testGetPetitionById_Found() {
        // Arrange
        petitionService.createPetition("Save the Forest", "A petition to save the rainforest.");
        Petition petition = petitionService.getAllPetitions().get(0);

        // Act
        Petition foundPetition = petitionService.getPetitionById(petition.getId());

        // Assert
        assertNotNull(foundPetition, "Petition should be found.");
        assertEquals(petition.getId(), foundPetition.getId(), "The petition ID should match.");
    }

    @Test
    void testGetPetitionById_NotFound() {
        // Act
        Petition petition = petitionService.getPetitionById(999); // Invalid ID

        // Assert
        assertNull(petition, "Petition should not be found.");
    }

    @Test
    void testSignPetition() {
        // Arrange
        petitionService.createPetition("Save the Earth", "A petition to save the Earth.");
        Petition petition = petitionService.getAllPetitions().get(0);

        // Act
        petitionService.signPetition(petition.getId(), "John Doe", "john.doe@example.com");

        // Assert
        assertEquals(1, petition.getSignatures().size(), "There should be one signature.");
        assertEquals("John Doe (john.doe@example.com)", petition.getSignatures().get(0), "The signature name should match.");
    }
}
