package de.evoila.personalAbteilung.loadDatabase;

import de.evoila.personalAbteilung.models.Candidate;
import de.evoila.personalAbteilung.models.Position;
import de.evoila.personalAbteilung.repositories.CandidateRepository;
import de.evoila.personalAbteilung.repositories.PositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CandidateRepository candidateRepository, PositionRepository positionRepository) {
        return args -> {

            Position p1 = positionRepository.save(new Position("Backend Developer", "ABC", 5500L));
            Position p2 = positionRepository.save(new Position("Frontend Developer", "ABC", 5000L));
            Position p3 = positionRepository.save(new Position("Data engineer", "ABC", 7000L));

            log.info("Preloading " + p1);
            log.info("Preloading " + p2);
            log.info("Preloading " + p3);

            Candidate c1 = candidateRepository.save(new Candidate("Matt", "Parker", "matt.parker@gmail.com", 4500L, p1));
            Candidate c2 = candidateRepository.save(new Candidate("Mary", "Jane", "mary.jane@gmail.com", 7500L, p1));
            Candidate c3 = candidateRepository.save(new Candidate("Tony", "Stark", "t.s@gmail.com", 3500L, p2));
            Candidate c4 = candidateRepository.save(new Candidate("Tom", "Holland", "t.holland@gmail.com", 3500L, p3));
            Candidate c5 = candidateRepository.save(new Candidate("Luise", "Portman", "luise.p@gmail.com", 8000L, p3));
            Candidate c6 = candidateRepository.save(new Candidate("Maria", "Lina", "maria.lina@gmail.com", 4000L, p3));

            log.info("Preloading " + c1);
            log.info("Preloading " + c2);
            log.info("Preloading " + c3);
            log.info("Preloading " + c4);
            log.info("Preloading " + c5);
            log.info("Preloading " + c6);

        };
    }

}
