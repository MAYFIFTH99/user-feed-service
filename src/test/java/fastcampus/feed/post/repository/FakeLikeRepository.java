package fastcampus.feed.post.repository;

import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.domain.comment.Comment;
import fastcampus.feed.post.repository.interfaces.LikeRepository;
import fastcampus.feed.user.domain.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FakeLikeRepository implements LikeRepository {

    Map<Post, Set<User>> postLikes = new HashMap<>();
    Map<Comment, Set<User>> commentLikes = new HashMap<>();

    @Override
    public boolean isAlreadyLiked(User user, Post post) {
        if (postLikes.get(post) == null) {
            return false;
        }
        return postLikes.get(post).contains(user);
    }

    @Override
    public boolean isAlreadyLiked(User user,Comment post) {
        if (commentLikes.get(post) == null) {
            return false;
        }
        return commentLikes.get(post).contains(user);
    }

    @Override
    public void save(User user,Post post) {
        Set<User> users = postLikes.get(post);
        if (users == null) {
            users = new HashSet<>();
        }
        users.add(user);
        postLikes.put(post, users);
    }

    @Override
    public void delete(User user,Post post) {
        Set<User> users = postLikes.get(post);
        if (users == null) {
            return;
        }
        users.remove(user);
        postLikes.put(post, users);
    }

    @Override
    public void save(User user,Comment comment) {
        Set<User> users = commentLikes.get(comment);
        if (users == null) {
            users = new HashSet<>();
        }
        users.add(user);
        commentLikes.put(comment, users);
    }

    @Override
    public void delete(User user,Comment comment) {
        Set<User> users = commentLikes.get(comment);
        if (users == null) {
            return;
        }
        users.remove(user);
        commentLikes.put(comment, users);
    }
}
