package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;


import static org.junit.jupiter.api.Assertions.*;

class PetitionTest {

    private Petition petition;

    @BeforeEach
    void setUp() {
        // Create a petition before each test
        petition = new Petition("More bicycle lanes in Malahide", "A petition for more bicycle lanes.");
    }

    // Test that the petition has the correct title and description
    @Test
    void testPetitionCreation() {
        assertEquals("More bicycle lanes in Malahide", petition.getTitle(), "Title should match.");
        assertEquals("A petition for more bicycle lanes.", petition.getDescription(), "Description should match.");
    }

    // Test that we can modify the title and description
    @Test
    void testSettersAndGetters() {
        petition.setTitle("More pedestrian areas in Malahide");
        petition.setDescription("A petition for more pedestrian areas.");

        assertEquals("More pedestrian areas in Malahide", petition.getTitle(), "Updated title should match.");
        assertEquals("A petition for more pedestrian areas.", petition.getDescription(), "Updated description should match.");
    }
}
