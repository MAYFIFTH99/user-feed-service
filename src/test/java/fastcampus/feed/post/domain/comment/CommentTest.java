package fastcampus.feed.post.domain.comment;

import static org.junit.jupiter.api.Assertions.*;

import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.domain.PostPublishState;
import fastcampus.feed.post.domain.content.CommentContent;
import fastcampus.feed.post.domain.content.PostContent;
import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommentTest {
    User user1;
    User user2;
    Post post1;
    Comment comment2;

    @BeforeEach
    void setUp() {
        UserInfo userInfo = new UserInfo("test1", "");
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
        PostContent postContent = new PostContent("testContent");
        post1 = new Post(1L, user1, postContent, PostPublishState.PUBLIC);

        CommentContent commentContent = new CommentContent("commentContent");
        comment2 = new Comment(1L, user2, post1, commentContent);
    }



    @Test
    void like() throws Exception {
        //given
        comment2.like(user1);

        //when & then
        assertEquals(comment2.getLikeCount().getCount(), 1);
    }

    @Test
    void like_self() throws Exception {
        assertThrows(IllegalStateException.class, () -> comment2.like(user2));
    }

    @Test
    void unlike() throws Exception {
        //given
        comment2.like(user1);
        //when
        comment2.unlike(user1);
        //then
        assertEquals(comment2.getLikeCount().getCount(), 0);

        comment2.unlike(user1);
        comment2.unlike(user1);
        assertEquals(comment2.getLikeCount().getCount(), 0);

    }


    @Test
    void update() throws Exception {
        //given
        comment2.updateComment(user2, "update comment");

        //when & then
        assertEquals(comment2.getContent().getContentText(), "update comment");
    }

    @Test
    void update_Ex() throws Exception {
        assertThrows(IllegalStateException.class,
                () -> comment2.updateComment(user1, "update comment"));


        assertThrows(IllegalStateException.class,
                () -> comment2.updateComment(user1, "a".repeat(101)));
    }
}