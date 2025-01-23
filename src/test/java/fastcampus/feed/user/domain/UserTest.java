package fastcampus.feed.user.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import fastcampus.feed.common.domain.PositiveIntegerCount;
import org.junit.jupiter.api.Test;

class UserTest {

    UserInfo userInfo1;
    UserInfo userInfo2;
    User user1;
    User user2;

    @BeforeEach
    void setUp() throws Exception {
        userInfo1 = new UserInfo("test1", "");
        user1 = new User(1L, userInfo1);
        userInfo2 = new UserInfo("test2", "");
        user2 = new User(2L, userInfo2);
    }

    @Test
    void equalsAndHashCode(){
        UserInfo userInfo = new UserInfo("test1", "");
        User user1 = new User(1L, userInfo);
        User user2 = new User(1L, userInfo);


        assertEquals(user1, user2);
    }

    @Test
    void follow_정상() throws Exception {
        //given
        UserInfo userInfo2 = new UserInfo("test2", "");
        User user2 = new User(2L, userInfo2);

        //when
        user1.follow(user2);

        //then
        assertEquals(user1.getFollowingCount(), 1);
        assertEquals(user2.getFollowerCount(), 1);
    }

    @Test
    void follow_자기자신_예외() throws Exception {
        assertThatThrownBy(() ->
                user1.follow(user1));
    }

    @Test
    void unfollow_정상() throws Exception {
        //given
        user1.follow(user2);
        //when
        user1.unfollow(user2);
        //then
        assertEquals(user1.getFollowingCount(), 0);
        assertEquals(user2.getFollowerCount(), 0);
    }

    @Test
    void unfollow_자기자신_예외() throws Exception {
        //given
        user1.follow(user2);
        //when
        assertThatThrownBy(() -> user1.unfollow(user1));
        //then
    }

}