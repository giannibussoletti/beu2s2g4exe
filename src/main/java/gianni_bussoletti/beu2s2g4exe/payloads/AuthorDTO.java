package gianni_bussoletti.beu2s2g4exe.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorDTO(
        @NotBlank(message = "Il campo nome non può essere lasciato vuoto")
        @Size(min = 2, max = 30, message = "Il nome deve essere compreso fra 2 e 30 caratteri")
        String name,
        @NotBlank(message = "Il campo cognome non può essere lasciato vuoto")
        @Size(min = 2, max = 30, message = "Il cognome deve essere compreso fra 2 e 30 caratteri")
        String surname,
        @Past(message = "La data deve essere antecedente a quella presente")
        LocalDate dateOfBirth,
        @NotBlank(message = "Il campo email non può essere lasciato vuoto")
        @Email(message = "L'email inserita non rispetta i requisiti minimi")
        String email) {
}
