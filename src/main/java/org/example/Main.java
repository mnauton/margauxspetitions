package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example"})
public class Main extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner initData(PetitionService petitionService) {
        return args -> {
            // Creating initial petitions for application start
            petitionService.createPetition(
                    "Improve Cycling Lanes in Malahide",
                    "Petition to create safer, dedicated cycling lanes in Malahide."
            );
            petitionService.createPetition(
                    "Install More Bike Racks in Malahide",
                    "A request to increase bike parking spots near public spaces in Malahide."
            );
            petitionService.createPetition(
                    "Enhance Bike Security in Malahide",
                    "Call for improved surveillance and anti-theft measures for bicycles in Malahide."
            );

            // Add random signatures to petitions
            petitionService.signPetition(1, "Alice Smith", "alice.smith@example.com");
            petitionService.signPetition(1, "Bob Johnson", "bob.johnson@example.com");

            petitionService.signPetition(2, "Charlie Brown", "charlie.brown@example.com");

            petitionService.signPetition(3, "Diana Prince", "diana.prince@example.com");
            petitionService.signPetition(3, "Ethan Hunt", "ethan.hunt@example.com");
            petitionService.signPetition(3, "Fiona Gallagher", "fiona.gallagher@example.com");
            petitionService.createPetition(
                    "Expand Public Library Hours",
                    "A petition to increase public library opening hours."
            );
            petitionService.signPetition(4, "Grace Harper", "grace.harper@example.com");
        };
    }
}
