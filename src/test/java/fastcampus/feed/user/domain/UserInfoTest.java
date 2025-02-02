package fastcampus.feed.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class UserInfoTest {

    @Test
    void 생성자_정상적으로_객체를_생성한다(){
        assertDoesNotThrow(() ->{
            new UserInfo("test", "");
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 생성자_이름이_null이거나_빈_문자열이면_예외를_던진다(String name) {
        assertThrows(IllegalArgumentException.class, () ->{
            new UserInfo(name, "");
        });
    }

}