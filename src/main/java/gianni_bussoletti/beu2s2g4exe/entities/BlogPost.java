package gianni_bussoletti.beu2s2g4exe.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Setter
@NoArgsConstructor
@ToString
@Table(name = "blog_posts")
public class BlogPost {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String cover;
    @Column(nullable = false)
    private String content;
    @Column(name = "reading_time", nullable = false)
    private double readingTime;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Author author;
    @Column(name = "published_at", nullable = false)
    private LocalDateTime publishedAt;

    public BlogPost(String category, String title, String content, double readingTime, Author author) {
        this.category = category;
        this.title = title;
        this.cover = "https://picsum.photos/500/200";
        this.content = content;
        this.readingTime = readingTime;
        this.author = author;
        this.publishedAt = LocalDateTime.now();

    }
}
