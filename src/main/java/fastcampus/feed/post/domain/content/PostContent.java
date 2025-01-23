package fastcampus.feed.post.domain.content;

import lombok.Getter;

@Getter
public class PostContent extends Content {
    private static final int MAX_LENGTH = 500;
    private static final int MIN_LENGTH = 5;
    private String content;

    public PostContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String content) {
        if (content == null || content.length() < MIN_LENGTH || content.length() > MAX_LENGTH) {
            throw new IllegalStateException();
        }
    }

}
