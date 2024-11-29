package org.example;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetitionService {

    private final List<Petition> petitions;

    public PetitionService() {
        this.petitions = new ArrayList<>();
    }

    // Add a new petition
    public void createPetition(String title, String description) {
        if (petitions.stream().anyMatch(p -> p.getTitle().equalsIgnoreCase(title))) {
            throw new IllegalArgumentException("A petition with this title already exists.");
        }
        petitions.add(new Petition(title, description));
    }

    // Get all petitions
    public List<Petition> getAllPetitions() {
        return petitions;
    }

    // Search for a petition by title
    public List<Petition> searchPetitionByTitle(String title) {
        return petitions.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList(); // Converts the Stream to a List
    }

    // Get a specific petition by ID
    public Petition getPetitionById(int id) {
        return petitions.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Add a signature to a petition
    public void signPetition(int id, String name, String email) {
        Petition petition = getPetitionById(id);
        if (petition != null) {
            petition.addSignature(name, email);
        }
    }
}
