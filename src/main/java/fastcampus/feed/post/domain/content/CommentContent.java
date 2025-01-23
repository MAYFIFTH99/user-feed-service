package fastcampus.feed.post.domain.content;

import fastcampus.feed.post.common.DateTimeInfo;

public class CommentContent extends Content {

    private static final int MAX_LENGTH = 100;


    public CommentContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String content) {
        if (content == null) {
            throw new IllegalStateException();
        }

        if (content.length() > MAX_LENGTH) {
            throw new IllegalStateException();
        }
    }


}
