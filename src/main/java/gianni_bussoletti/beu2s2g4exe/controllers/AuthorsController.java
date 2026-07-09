package gianni_bussoletti.beu2s2g4exe.controllers;

import gianni_bussoletti.beu2s2g4exe.entities.Author;
import gianni_bussoletti.beu2s2g4exe.exceptions.ValidationException;
import gianni_bussoletti.beu2s2g4exe.payloads.AuthorDTO;
import gianni_bussoletti.beu2s2g4exe.payloads.AuthorResponseDTO;
import gianni_bussoletti.beu2s2g4exe.services.AuthorsService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    private final AuthorsService authorsService;

    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponseDTO saveAuthor(@RequestBody @Validated AuthorDTO payload, BindingResult validation) {
        if (validation.hasErrors()) {
            List<String> errorsMessage = validation.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new ValidationException(errorsMessage);
        }
        Author saved = this.authorsService.save(payload);
        return new AuthorResponseDTO(saved.getId(), LocalDateTime.now());
    }

    @GetMapping
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "name") String orderBy) {
        return this.authorsService.findAllAuthors(page, size, orderBy);
    }

    @PatchMapping("/{authorId}/avatar")
    public Author updateProfilePic(@PathVariable UUID authorId, @RequestParam("profile_picture") MultipartFile file) {
        return this.authorsService.UpdateAvatarAuthor(authorId, file);
    }
}
