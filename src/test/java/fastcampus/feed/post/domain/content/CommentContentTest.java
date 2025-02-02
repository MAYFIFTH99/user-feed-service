package fastcampus.feed.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class CommentContentTest {
    @Test
    void 생성자_정상() throws Exception {
        //given
        CommentContent commentContent = new CommentContent("normal content");
        //when
        String getContent = commentContent.getContentText();
        //then
        assertEquals(getContent, "normal content");
    }
    @Test
    void 생성자_100자_이상() throws Exception {
        //given
        String content = "a".repeat(101);

        //when & then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 생성자_Null_And_Empty(String ex) throws Exception {
        //given
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(ex));
        //when

        //then
    }

}