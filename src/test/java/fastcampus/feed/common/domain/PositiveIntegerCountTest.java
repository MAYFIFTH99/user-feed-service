package fastcampus.feed.common.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PositiveIntegerCountTest {

    @Test
    void increase_정상() {
        PositiveIntegerCount positiveIntegerCount = new PositiveIntegerCount();
        positiveIntegerCount.increase();
        assertEquals(positiveIntegerCount.getCount(),  1);
    }

    @Test
    void decrease_정상() {
        PositiveIntegerCount positiveIntegerCount = new PositiveIntegerCount(1);
        positiveIntegerCount.decrease();
        assertEquals(positiveIntegerCount.getCount(), 0);
    }

    @Test
    void decrease_예외(){
        //given
        PositiveIntegerCount positiveIntegerCount = new PositiveIntegerCount(1);
        positiveIntegerCount.decrease();
        //when
        positiveIntegerCount.decrease();
        positiveIntegerCount.decrease();
        //then
        assertEquals(positiveIntegerCount.getCount(), 0);

    }
}