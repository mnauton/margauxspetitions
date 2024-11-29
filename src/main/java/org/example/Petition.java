package org.example;

import java.util.ArrayList;
import java.util.List;

public class Petition {
    private static int idCounter = 1; // Static counter to assign unique IDs
    private final int id; // Unique ID for each petition
    private String title;
    private String description;
    private final List<Signature> signatures;

    public Petition(String title, String description) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        this.signatures = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Signature> getSignatures() {
        return signatures;
    }

    public void addSignature(Signature signature) {
        signatures.add(signature);
    }
}
