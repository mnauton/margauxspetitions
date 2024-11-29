package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/petitions")
public class PetitionController {

    private final PetitionService petitionService;

    @Autowired
    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    @RequestMapping("/")
    public String redirectToAllPetitions() {
        return "redirect:/petitions/all";
    }

    @GetMapping("/all")
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitions", petitionService.getAllPetitions());
        return "petition/all";
    }

    @GetMapping("/create")
    public String petitionCreationForm() {
        return "petition/create";
    }

    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description) {
        petitionService.createPetition(title, description);
        return "redirect:/petitions/all";
    }

    @GetMapping("/{id}")
    public String viewPetition(@PathVariable int id, Model model) {
        Petition petition = petitionService.getPetitionById(id);
        model.addAttribute("petition", petition);
        return "petition/view";
    }

    @PostMapping("/{id}/sign")
    public String signPetition(@PathVariable int id, @RequestParam String name, @RequestParam String email) {
        petitionService.signPetition(id, name, email);
        return "redirect:/petitions/" + id;
    }

    @GetMapping("/search")
    public String showSearchForm() {
        return "petition/search";
    }

    @GetMapping("/search-result")
    public String searchPetition(@RequestParam String title, Model model) {
        List<Petition> petitions = petitionService.searchPetitionByTitle(title);
        model.addAttribute("petitions", petitions);
        return "petition/result";
    }
}
