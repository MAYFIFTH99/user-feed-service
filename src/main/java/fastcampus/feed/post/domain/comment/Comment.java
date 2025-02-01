package fastcampus.feed.post.domain.comment;

import fastcampus.feed.common.domain.PositiveIntegerCount;
import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.domain.content.Content;
import fastcampus.feed.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Comment {

    private final Long id;
    private final User author;
    private final Post post;
    private final Content content;
    private final PositiveIntegerCount likeCount;

    public Comment(Long id, User author, Post post, Content content) {
        validateNotNull(author, "작성자");
        validateNotNull(post, "게시글");
        validateNotNull(content, "댓글 내용");

        this.id = id;
        this.author = author;
        this.post = post;
        this.content = content;
        this.likeCount = new PositiveIntegerCount();
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalStateException();
        }
        likeCount.increase();
    }

    public void unlike(User user) {
        likeCount.decrease();
    }

    public void updateComment(User user, String updateComment) {

        if (!author.equals(user)) {
            throw new IllegalArgumentException("작성자만 댓글을 수정할 수 있습니다.");
        }
        content.updateContent(updateComment);
    }

    private void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("%s를 찾을 수 없습니다.", fieldName));
        }

    }
}
