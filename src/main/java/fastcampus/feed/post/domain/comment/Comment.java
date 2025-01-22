package fastcampus.feed.post.domain.comment;

import fastcampus.feed.common.domain.PositiveIntegerCount;
import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.domain.content.Content;
import fastcampus.feed.user.domain.User;

public class Comment {

    private final Long id;
    private final User author;
    private final Post post;
    private final Content content;
    private final PositiveIntegerCount likeCount;

    public Comment(Long id, User author, Post post, Content content) {
        if (author == null) {
            throw new IllegalStateException();
        }
        if (post == null) {
            throw new IllegalStateException();
        }

        if (content == null) {
            throw new IllegalStateException();
        }

        this.id = id;
        this.author = author;
        this.post = post;
        this.content = content;
        this.likeCount = new PositiveIntegerCount();
    }

    public void like(User user){
        if(this.author.equals(user)){
            throw new IllegalStateException();
        }
        likeCount.increase();
    }

    public void unlike(User user){
        likeCount.decrease();
    }

    public void updateComment(User user, String updateComment){
        if(!author.equals(user)){
            throw new IllegalStateException();
        }
        content.updateContent(updateComment);
    }
}
