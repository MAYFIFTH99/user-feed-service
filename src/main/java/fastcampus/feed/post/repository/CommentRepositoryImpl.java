package fastcampus.feed.post.repository;

import fastcampus.feed.post.domain.comment.Comment;
import fastcampus.feed.post.repository.entity.comment.CommentEntity;
import fastcampus.feed.post.repository.interfaces.CommentRepository;
import fastcampus.feed.post.repository.jpa.JpaCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;

    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment);
        return jpaCommentRepository.save(commentEntity).toComment();
    }

    @Override
    public Comment findById(Long id) {
        return jpaCommentRepository.findById(id).orElseThrow(IllegalArgumentException::new).toComment();
    }
}
