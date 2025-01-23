package fastcampus.feed.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class UserInfoTest {

    @Test
    void 생성자_정상(){
        assertDoesNotThrow(() ->{
            UserInfo userInfo = new UserInfo("test", "");
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 생성자_이름_null(String name) {
        assertThrows(IllegalStateException.class, () ->{
            UserInfo userInfo = new UserInfo(name, "");
        });
    }

}