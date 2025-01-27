package fastcampus.feed.post.service;

import static org.junit.jupiter.api.Assertions.*;

import fastcampus.feed.fake.FakeObjectFactory;
import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.domain.PostPublishState;
import fastcampus.feed.post.service.dto.CreatePostRequestDto;
import fastcampus.feed.post.service.dto.LikePostRequestDto;
import fastcampus.feed.post.service.dto.UpdatePostRequestDto;
import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.service.UserService;
import fastcampus.feed.user.service.dto.CreateUserRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostServiceTest {

    private final PostService postService = FakeObjectFactory.getPostService();
    private final UserService userService = FakeObjectFactory.getUserService();
    User user1;
    User user2;
    Post post1;


    @BeforeEach
    void setUp() {
        CreateUserRequestDto userDto = new CreateUserRequestDto("name", "profileImageUrl");
        user1 = userService.createUser(userDto);
        user2 = userService.createUser(userDto);

        CreatePostRequestDto postDto = new CreatePostRequestDto(user1.getId(), "postContent",
                PostPublishState.PUBLIC);
        post1 = postService.createPost(postDto);
    }


    @Test
    void create_post() throws Exception {
        //given
        CreatePostRequestDto postDto = new CreatePostRequestDto(user1.getId(), "postContent",
                PostPublishState.PUBLIC);
        //when
        post1 = postService.createPost(postDto);

        //then
        assertEquals(postService.getPost(post1.getId()), post1);
    }

    @Test
    void getPost() throws Exception {
        //given

        //when
        Post post = postService.getPost(post1.getId());
        //then
        assertEquals(post, post1);
    }

    @Test
    void updatePost() throws Exception {
        //given
        UpdatePostRequestDto updateDto = new UpdatePostRequestDto(user1.getId(), post1.getId(), "updatedContent",
                PostPublishState.PRIVATE);
        postService.updatePost(updateDto);

        //when
        Post updatedPost = postService.getPost(post1.getId());

        //then
        assertEquals(updatedPost.getPostContent().getContentText(), "updatedContent");
        assertEquals(updatedPost.getPostPublishState(), PostPublishState.PRIVATE);

    }

    @Test
    void likePost() throws Exception {
        //given
        LikePostRequestDto likeDto = new LikePostRequestDto(user2.getId(), post1.getId());
        //when
        postService.likePost(likeDto);
        //then
        assertEquals(post1.getLikeCount().getCount(), 1);

    }
    @Test
    void like_already_post() throws Exception {
        //given
        LikePostRequestDto likeDto = new LikePostRequestDto(user2.getId(), post1.getId());
        //when
        postService.likePost(likeDto);
        //then
        assertEquals(post1.getLikeCount().getCount(), 1);
    }

    @Test
    void unlike() throws Exception {
        //given
        LikePostRequestDto likeDto = new LikePostRequestDto(user2.getId(), post1.getId());
        postService.likePost(likeDto);

        //when
        postService.unlikePost(likeDto);
        //then
        assertEquals(post1.getLikeCount().getCount(), 0);

        postService.unlikePost(likeDto);
        assertEquals(post1.getLikeCount().getCount(), 0);

    }
}