package fastcampus.feed.post.domain.content;

public class CommentContent extends Content {

    private static final int MAX_LENGTH = 100;


    public CommentContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String content) {
        if (content == null || content.isEmpty() || content.isBlank()) {
            throw new IllegalArgumentException("댓글 내용은 공백일 수 없습니다.");
        }

        if (content.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("댓글 길이는 100자 이하여야 합니다.");
        }
    }


}
