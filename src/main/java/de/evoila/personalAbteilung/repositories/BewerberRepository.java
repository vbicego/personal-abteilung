package de.evoila.personalAbteilung.repositories;

import de.evoila.personalAbteilung.models.Bewerber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BewerberRepository extends JpaRepository<Bewerber, Long> {
}
