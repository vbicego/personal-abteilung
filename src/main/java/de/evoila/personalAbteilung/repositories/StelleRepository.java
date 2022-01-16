package de.evoila.personalAbteilung.repositories;

import de.evoila.personalAbteilung.models.Stelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StelleRepository extends JpaRepository<Stelle,Long> {
}
