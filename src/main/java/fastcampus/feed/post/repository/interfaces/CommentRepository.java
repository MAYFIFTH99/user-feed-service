package fastcampus.feed.post.repository.interfaces;

import fastcampus.feed.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);
    Comment findById(Long id);

}
