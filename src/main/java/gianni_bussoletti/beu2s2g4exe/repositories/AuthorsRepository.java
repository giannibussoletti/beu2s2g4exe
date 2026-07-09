package gianni_bussoletti.beu2s2g4exe.repositories;

import gianni_bussoletti.beu2s2g4exe.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, UUID> {

    boolean existsByEmail(String mail);

    Author findAuthorById(UUID authorId);
}
