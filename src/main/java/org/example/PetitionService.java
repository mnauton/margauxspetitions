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
}
