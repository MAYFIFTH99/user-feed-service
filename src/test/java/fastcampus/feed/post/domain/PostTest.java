package fastcampus.feed.post.domain;

import static org.junit.jupiter.api.Assertions.*;

import fastcampus.feed.post.domain.content.PostContent;
import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostTest {

    User user1;
    User user2;
    Post post1;

    @BeforeEach
    void setUp() {
        UserInfo userInfo = new UserInfo("test1", "");
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
        PostContent postContent = new PostContent("testContent");
        post1 = new Post(1L, user1, postContent, PostPublishState.PUBLIC);
    }
    @Test
    void like_정상() throws Exception {
        //when
        post1.like(user2);

        //then
        assertEquals(post1.getLikeCount().getCount(),1);
    }

    @Test
    void like_self() throws Exception {
        //when & then
        assertThrows(IllegalArgumentException.class,() -> post1.like(user1));
    }

    @Test
    void unlike_정상() throws Exception {
        //given
        post1.like(user2);

        //when
        post1.unlike(user2);

        //then
        assertEquals(post1.getLikeCount().getCount(),0);
    }
    @Test
    void unlike_0미만() throws Exception {
        //given
        post1.like(user2);
        //when
        post1.unlike(user2);
        post1.unlike(user2);
        post1.unlike(user2);

        //then
        assertEquals(post1.getLikeCount().getCount(),0);
    }


    @Test
    void update() throws Exception {
        //when
        post1.updatePost(user1, "update content", PostPublishState.PUBLIC);

        //then
        assertEquals(post1.getPostContent().getContentText(), "update content");
    }

    @Test
    void update_예외() throws Exception {
        //when & then
        assertThrows(IllegalArgumentException.class, () -> post1.updatePost(user2, "update content", PostPublishState.PUBLIC));
    }
}