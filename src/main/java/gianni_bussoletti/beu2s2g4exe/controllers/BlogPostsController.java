package gianni_bussoletti.beu2s2g4exe.controllers;

import gianni_bussoletti.beu2s2g4exe.entities.BlogPost;
import gianni_bussoletti.beu2s2g4exe.payloads.PostDTO;
import gianni_bussoletti.beu2s2g4exe.payloads.PostResponseDTO;
import gianni_bussoletti.beu2s2g4exe.services.PostsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/posts")
public class BlogPostsController {

    private final PostsService postsService;

    public BlogPostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDTO saveBlog(@RequestBody PostDTO payload) {
        BlogPost newBlogPost = this.postsService.save(payload);
        return new PostResponseDTO(newBlogPost.getId(), LocalDateTime.now());
    }

    @GetMapping
    public Page<BlogPost> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "title") String orderBy) {
        return this.postsService.findAll(page, size, orderBy);
    }
}
