package fastcampus.feed.post.repository;

import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.repository.entity.post.PostEntity;
import fastcampus.feed.post.repository.interfaces.PostRepository;
import fastcampus.feed.post.repository.jpa.JpaPostRepository;
import fastcampus.feed.post.repository.post_queue.UserPostQueueCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;

    @Override
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
        PostEntity savedEntity = jpaPostRepository.save(postEntity);
        userPostQueueCommandRepository.publishPost(savedEntity);
        return savedEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        return jpaPostRepository.findById(id).orElseThrow(IllegalArgumentException::new).toPost();
    }
}
