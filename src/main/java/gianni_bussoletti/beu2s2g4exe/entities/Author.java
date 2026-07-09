package gianni_bussoletti.beu2s2g4exe.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "date_birth", nullable = false)
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private String avatar;


    public Author(String name, String surname, LocalDate dateOfBirth, String email) {
        this.avatar = "https://picsum.photos/200";
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }
}
