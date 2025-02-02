package fastcampus.feed.post.domain.content;

import lombok.Getter;

@Getter
public class PostContent extends Content {
    private static final int MAX_LENGTH = 500;
    private static final int MIN_LENGTH = 5;

    public PostContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("게시글 내용은 공백일 수 없습니다.");
        }
        if(content.length() < MIN_LENGTH || content.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("게시글 길이는 5자 이상 500자 이하여야 합니다.");
        }
    }

}
