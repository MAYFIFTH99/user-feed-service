package fastcampus.feed.post.repository;

import fastcampus.feed.post.domain.comment.Comment;
import fastcampus.feed.post.repository.entity.comment.CommentEntity;
import fastcampus.feed.post.repository.entity.post.PostEntity;
import fastcampus.feed.post.repository.interfaces.CommentRepository;
import fastcampus.feed.post.repository.jpa.JpaCommentRepository;
import fastcampus.feed.post.repository.jpa.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    public Comment save(Comment comment) {
        PostEntity postEntity = jpaPostRepository.findById(comment.getPost().getId())
                .orElseThrow(() -> new IllegalArgumentException("POST를 찾을 수 없습니다."));
        postEntity.increaseCommentCount(); // 변경 감지
        CommentEntity commentEntity = new CommentEntity(comment);
        return jpaCommentRepository.save(commentEntity).toComment();
    }

    @Override
    public Comment findById(Long id) {
        return jpaCommentRepository.findById(id).orElseThrow(IllegalArgumentException::new).toComment();
    }
}
