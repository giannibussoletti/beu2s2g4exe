package gianni_bussoletti.beu2s2g4exe.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import gianni_bussoletti.beu2s2g4exe.entities.Author;
import gianni_bussoletti.beu2s2g4exe.exceptions.AuthorAlreadyPresentException;
import gianni_bussoletti.beu2s2g4exe.payloads.AuthorDTO;
import gianni_bussoletti.beu2s2g4exe.repositories.AuthorsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthorsService {
    private final AuthorsRepository authorsRepository;
    private final Cloudinary fileUploader;

    public AuthorsService(AuthorsRepository authorsRepository, Cloudinary fileUploader) {
        this.authorsRepository = authorsRepository;
        this.fileUploader = fileUploader;
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

    public void UpdateAvatarAuthor(UUID AuthorID, MultipartFile file) {
        try {
            Map info = fileUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) info.get("secure_url");
            this.findByID(AuthorID).setAvatar(url);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
