package fastcampus.feed.post.repository;

import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.repository.interfaces.PostRepository;
import java.util.HashMap;
import java.util.Map;

public class FakePostRepository implements PostRepository {
    Map<Long, Post> store = new HashMap<>();
    Long sequence = 1L;

    @Override
    public Post save(Post post) {
        if (post.getId() != null) {
            store.put(post.getId(), post);
        }
        Post newPost = new Post(sequence, post.getAuthor(), post.getPostContent(),
                post.getPostPublishState());
        store.put(sequence++, newPost);

        return newPost;
    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }
}
