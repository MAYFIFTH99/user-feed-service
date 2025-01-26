package fastcampus.feed.post.repository.interfaces;

import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.domain.comment.Comment;
import fastcampus.feed.user.domain.User;

public interface LikeRepository {
    boolean isAlreadyLiked(User user, Post post);
    void save(User user, Post post);
    void delete(User user, Post post);

    boolean isAlreadyLiked(User user, Comment comment);
    void save(User user, Comment comment);
    void delete(User user, Comment comment);

}
