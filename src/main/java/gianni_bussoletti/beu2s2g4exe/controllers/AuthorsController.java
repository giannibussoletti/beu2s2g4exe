package gianni_bussoletti.beu2s2g4exe.controllers;

import gianni_bussoletti.beu2s2g4exe.entities.Author;
import gianni_bussoletti.beu2s2g4exe.payloads.AuthorDTO;
import gianni_bussoletti.beu2s2g4exe.payloads.AuthorResponseDTO;
import gianni_bussoletti.beu2s2g4exe.services.AuthorsService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
            List<String> errorsMessage = validation.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getCode).toList();
        }
        Author saved = this.authorsService.save(payload);
        return new AuthorResponseDTO(saved.getId(), LocalDateTime.now());
    }

    @GetMapping
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "name") String orderBy) {
        return this.authorsService.findAllAuthors(page, size, orderBy);
    }
}
