package fastcampus.feed.post.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DateTimeInfoTest {

    @Test
    void 생성시점_isEdited_isFalse() throws Exception {
        //given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        //when
        boolean edited = dateTimeInfo.isEdited();
        //then
        assertFalse(edited);
    }

    @Test
    void update() throws Exception {
        //given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        //when
        dateTimeInfo.update();
        //then
        assertTrue(dateTimeInfo.isEdited());
    }

}