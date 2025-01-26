package fastcampus.feed.post.domain.content;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    @Test
    void postContent() throws Exception {

        //then
        assertDoesNotThrow(() -> new PostContent("5글자 이상"));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void postContentException(String ex) throws Exception {
        assertThrows(IllegalStateException.class, () -> new PostContent(ex));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a, b"})
    void 글자_500이상_오류(String ex) throws Exception {
        String content = ex.repeat(501);
        //given
        assertThrows(IllegalStateException.class, () -> new PostContent(content));

        //when

        //then
    }
}