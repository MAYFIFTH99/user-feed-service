package fastcampus.feed.post.repository;

import fastcampus.feed.post.domain.comment.Comment;
import fastcampus.feed.post.repository.interfaces.CommentRepository;
import java.util.HashMap;
import java.util.Map;

public class FakeCommentRepository implements CommentRepository {
    Map<Long, Comment> store = new HashMap<>();
    Long sequence = 1L;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null) {
            store.put(comment.getId(), comment);
        }
        Comment newComment = new Comment(sequence++, comment.getAuthor(), comment.getPost(),
                comment.getContent());
        return newComment;
    }


    @Override
    public Comment findById(Long id) {
        return store.get(id);
    }
}
