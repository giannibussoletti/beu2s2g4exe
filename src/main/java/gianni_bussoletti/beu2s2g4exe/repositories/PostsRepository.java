package gianni_bussoletti.beu2s2g4exe.repositories;

import gianni_bussoletti.beu2s2g4exe.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostsRepository extends JpaRepository<BlogPost, UUID> {
}
