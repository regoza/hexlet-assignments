package exercise;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> index(@RequestParam(defaultValue = "10") int limit) {
        return posts.stream().limit(limit).collect(Collectors.toList());
    }

    @GetMapping("/posts/{postId}")
    public Optional<Post> show(@PathVariable String postId) {
        return posts.stream().filter(post -> post.getId().equals(postId)).findFirst();
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{postId}")
    public Post update(@PathVariable String postId, @RequestBody Post post) {
        Optional<Post> result = posts.stream().filter(p -> p.getId().equals(postId)).findFirst();
        if (result.isPresent()) {
            Post oldPost = result.get();
            oldPost.setBody(post.getBody());
            oldPost.setTitle(post.getTitle());
        }
        return post;
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable String postId) {
        posts.removeIf(el -> el.getId().equals(postId));
    }
    // END
}
