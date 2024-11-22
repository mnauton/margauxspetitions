package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/petitions")
public class PetitionController {

    private final PetitionService petitionService;

    @Autowired
    public PetitionController(PetitionService petitionService) {
        // Injecting the PetitionService into the controller
        this.petitionService = petitionService;
    }

    // Home Page
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    // View All Petitions
    @GetMapping("/all")
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitions", petitionService.getAllPetitions());
        return "petition/all";
    }

    // Form for Creating a Petition
    @GetMapping("/create")
    public String petitionCreationForm() {
        return "petition/create";
    }

    // Create a Petition
    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description) {
        petitionService.createPetition(title, description);
        return "redirect:/petitions/all";
    }
}
