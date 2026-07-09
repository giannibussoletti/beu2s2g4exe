package gianni_bussoletti.beu2s2g4exe.services;

import gianni_bussoletti.beu2s2g4exe.entities.Author;
import gianni_bussoletti.beu2s2g4exe.exceptions.AuthorAlreadyPresentException;
import gianni_bussoletti.beu2s2g4exe.payloads.AuthorDTO;
import gianni_bussoletti.beu2s2g4exe.repositories.AuthorsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorsService {
    private final AuthorsRepository authorsRepository;

    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public Author save(AuthorDTO payload) {
        if (this.authorsRepository.existsByEmail(payload.email()))
            throw new AuthorAlreadyPresentException("L'email dell'autore risulta già registrata");

        Author newAuthor = new Author(payload.name(), payload.surname(), payload.dateOfBirth(), payload.email());
        return this.authorsRepository.save(newAuthor);
    }

    public Page<Author> findAllAuthors(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.authorsRepository.findAll(pageable);
    }

    public Author findByID(UUID id) {
        return this.authorsRepository.findAuthorById(id);

    }
}
