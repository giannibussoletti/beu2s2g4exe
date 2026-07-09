package gianni_bussoletti.beu2s2g4exe.services;

import gianni_bussoletti.beu2s2g4exe.entities.Author;
import gianni_bussoletti.beu2s2g4exe.entities.BlogPost;
import gianni_bussoletti.beu2s2g4exe.payloads.PostDTO;
import gianni_bussoletti.beu2s2g4exe.repositories.PostsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostsService {
    private final AuthorsService authorsService;
    private final PostsRepository postsRepository;

    public PostsService(PostsRepository postsRepository, AuthorsService authorsService) {
        this.postsRepository = postsRepository;
        this.authorsService = authorsService;
    }

    public BlogPost save(PostDTO payload) {
        Author author = this.authorsService.findByID(payload.authorId());
        BlogPost newPost = new BlogPost(payload.category(), payload.title(), payload.content(), payload.readingTime(), author);
        return this.postsRepository.save(newPost);

    }

    public Page<BlogPost> findAll(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.postsRepository.findAll(pageable);
    }
}
