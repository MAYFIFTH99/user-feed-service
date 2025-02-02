package fastcampus.feed.post.domain;

import fastcampus.feed.common.domain.PositiveIntegerCount;
import fastcampus.feed.post.domain.content.PostContent;
import fastcampus.feed.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Post {

    private final Long id;
    private final User author;
    private final PostContent postContent;
    private PostPublishState postPublishState;
    private final PositiveIntegerCount likeCount;

    public Post(Long id, User author, PostContent postContent, PostPublishState postPublishState) {
        validateNotNull(author, "작성자");
        validateNotNull(postContent, "게시글 내용");

        this.id = id;
        this.author = author;
        this.postContent = postContent;
        this.postPublishState = postPublishState;
        this.likeCount = new PositiveIntegerCount();
    }

    public void like(User user) {
        if (user == null) {
            throw new IllegalStateException("좋아요를 누른 사용자를 찾을 수 없습니다.");
        }

        if (this.author.equals(user)) {
            throw new IllegalStateException("자신의 게시글에는 좋아요를 누를 수 없습니다.");
        }

        likeCount.increase();
    }

    public void unlike(User user) {
        if (user == null) {
            throw new IllegalStateException("좋아요를 취소한 사용자를 찾을 수 없습니다.");
        }
        likeCount.decrease();
    }

    public void updatePost(User user, String content, PostPublishState postPublishState) {
        validateNotNull(user, "사용자");
        if (!author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.postContent.updateContent(content);
        this.postPublishState = postPublishState;
    }

    private void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("%s를 찾을 수 없습니다.", fieldName));
        }
    }
}
