package gianni_bussoletti.beu2s2g4exe.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record PostDTO(@NotEmpty(message = "La categoria non può essere lasciata vuota")
                      String category,
                      @NotEmpty(message = "Il post deve avere un titolo")
                      String title,
                      @NotEmpty(message = "Il contenuto del post non può essere lasciato vuoto")
                      @Size(min = 300, max = 5000, message = "Il contenuto del post deve essere compreso fra 300 e 5000 caratteri")
                      String content,
                      @NotNull(message = "Il post deve indicare il tempo di lettura medio")
                      double readingTime,
                      @NotBlank(message = "Il campo ID dell'autore non può essere lasciato vuoto")
                      UUID authorId) {
}
