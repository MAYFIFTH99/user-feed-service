package fastcampus.feed.post.domain;

import fastcampus.feed.common.domain.PositiveIntegerCount;
import fastcampus.feed.post.domain.content.PostContent;
import fastcampus.feed.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Post {

    private final Long id;
    private final User author;
    private final PostContent postContent;
    private PostPublishState postPublishState;
    private final PositiveIntegerCount likeCount;

    public Post(Long id, User author, PostContent postContent, PostPublishState postPublishState) {
        if (author == null) {
            throw new IllegalArgumentException("user is null");
        }

        if (postContent == null) {
            throw new IllegalArgumentException("postContent is null");
        }

        this.id = id;
        this.author = author;
        this.postContent = postContent;
        this.postPublishState = postPublishState;
        this.likeCount = new PositiveIntegerCount();
    }

    public void like(User user){
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        if (user == null) {
            throw new IllegalArgumentException();
        }
       likeCount.increase();
    }

    public void unlike(User user){
        likeCount.decrease();
    }

    public void updatePost(User user, String content, PostPublishState postPublishState) {
        if(!author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.postContent.updateContent(content);
        this.postPublishState = postPublishState;
    }


}
