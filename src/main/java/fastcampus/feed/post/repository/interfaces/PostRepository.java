package fastcampus.feed.post.repository.interfaces;

import fastcampus.feed.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);

}
