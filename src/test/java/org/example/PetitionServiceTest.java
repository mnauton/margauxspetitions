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
        List<Petition> results = petitionService.searchPetitionByTitle("Save the Planet");

        // Assert
        assertFalse(results.isEmpty(), "Petition should be found.");
        assertEquals(1, results.size(), "There should be exactly one matching petition.");
        assertEquals("Save the Planet", results.get(0).getTitle(), "The title should match.");
    }

    @Test
    void testSearchPetitionByTitle_NotFound() {
        // Arrange
        petitionService.createPetition("Save the Planet", "A petition to save the Earth.");

        // Act
        List<Petition> results = petitionService.searchPetitionByTitle("Save the Forest");

        // Assert
        assertTrue(results.isEmpty(), "No petition should be found.");
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
        Signature signature = petition.getSignatures().get(0);
        assertEquals("John Doe", signature.getName(), "The signature name should match.");
        assertEquals("john.doe@example.com", signature.getEmail(), "The signature email should match.");
    }


    @Test
    void testCreatePetitionWithDuplicateTitle_ThrowsException() {
        // Arrange
        String title = "Save the Trees";
        String description1 = "A petition to save trees in our city park.";
        String description2 = "A different petition with the same title.";

        petitionService.createPetition(title, description1);

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            petitionService.createPetition(title, description2);
        });

        assertEquals("A petition with this title already exists.", exception.getMessage(),
                "Exception message should indicate a duplicate title.");
    }

    @Test
    void testCreatePetitionWithUniqueTitle_Succeeds() {
        // Arrange
        String title1 = "Save the Trees";
        String description1 = "A petition to save trees in our city park.";
        String title2 = "Protect the Wetlands";
        String description2 = "A petition to protect our wetlands.";

        // Act
        petitionService.createPetition(title1, description1);
        petitionService.createPetition(title2, description2);

        // Assert
        assertEquals(2, petitionService.getAllPetitions().size(), "Both petitions should be added successfully.");
        assertEquals(title1, petitionService.getAllPetitions().get(0).getTitle(), "First title should match.");
        assertEquals(title2, petitionService.getAllPetitions().get(1).getTitle(), "Second title should match.");
    }

    @Test
    void testSearchPetitionByTitle_SubstringMatch_Found() {
        // Arrange
        petitionService.createPetition("Save the Rainforest", "A petition to save the rainforest.");
        petitionService.createPetition("Save the Ocean", "A petition to protect the oceans.");
        petitionService.createPetition("Protect the Wetlands", "A petition to preserve wetlands.");

        // Act
        List<Petition> results = petitionService.searchPetitionByTitle("Save");

        // Assert
        assertEquals(2, results.size(), "There should be two petitions matching the substring 'Save'.");
        assertTrue(results.stream().anyMatch(p -> p.getTitle().equals("Save the Rainforest")),
                "The 'Save the Rainforest' petition should match.");
        assertTrue(results.stream().anyMatch(p -> p.getTitle().equals("Save the Ocean")),
                "The 'Save the Ocean' petition should match.");
    }

    @Test
    void testSearchPetitionByTitle_SubstringMatch_CaseInsensitive() {
        // Arrange
        petitionService.createPetition("Save the Planet", "A petition to save the Earth.");
        petitionService.createPetition("Protect the Planet", "A petition to protect the Earth.");

        // Act
        List<Petition> results = petitionService.searchPetitionByTitle("planet");

        // Assert
        assertEquals(2, results.size(), "There should be two petitions matching the substring 'planet' case-insensitively.");
        assertTrue(results.stream().anyMatch(p -> p.getTitle().equals("Save the Planet")),
                "The 'Save the Planet' petition should match.");
        assertTrue(results.stream().anyMatch(p -> p.getTitle().equals("Protect the Planet")),
                "The 'Protect the Planet' petition should match.");
    }

    @Test
    void testSearchPetitionByTitle_NoMatch() {
        // Arrange
        petitionService.createPetition("Save the Tigers", "A petition to protect tigers.");

        // Act
        List<Petition> results = petitionService.searchPetitionByTitle("Lions");

        // Assert
        assertTrue(results.isEmpty(), "There should be no petitions matching the substring 'Lions'.");
    }
}
