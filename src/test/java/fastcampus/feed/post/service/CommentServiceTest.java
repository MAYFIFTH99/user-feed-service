package fastcampus.feed.post.service;

import static org.junit.jupiter.api.Assertions.*;

import fastcampus.feed.fake.FakeObjectFactory;
import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.domain.PostPublishState;
import fastcampus.feed.post.domain.comment.Comment;
import fastcampus.feed.post.service.dto.CreateCommentRequestDto;
import fastcampus.feed.post.service.dto.CreatePostRequestDto;
import fastcampus.feed.post.service.dto.LikeCommentRequestDto;
import fastcampus.feed.post.service.dto.UpdateCommentRequestDto;
import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.service.UserService;
import fastcampus.feed.user.service.dto.CreateUserRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommentServiceTest {

    private final CommentService commentService = FakeObjectFactory.getCommentService();
    private final PostService postService = FakeObjectFactory.getPostService();
    private final UserService userService = FakeObjectFactory.getUserService();

    User user1;
    User user2;
    Post post1;
    CreateCommentRequestDto commentDto;

    @BeforeEach
    void setUp() {
        user1 = userService.createUser(new CreateUserRequestDto("name", "profileImageUrl"));
        user2 = userService.createUser(new CreateUserRequestDto("name", "profileImageUrl"));
        post1 = postService.createPost(new CreatePostRequestDto(user1.getId(), "postContent", PostPublishState.PUBLIC));
        commentDto = new CreateCommentRequestDto(user1.getId(), post1.getId(), "commentContent");
    }


    @Test
    void create() throws Exception {
        //given
        setUp();
        //when
        Comment comment = commentService.createComment(commentDto);
        //then
        assertNotNull(commentService.getComment(comment.getId()));
        assertEquals(comment.getAuthor(), user1);
        assertEquals(comment.getPost(), post1);
        assertEquals(comment.getContent().getContentText(), "commentContent");
    }

    @Test
    void get() throws Exception {
        //given
        Comment savedComment = commentService.createComment(commentDto);
        //when
        Comment getComment = commentService.getComment(savedComment.getId());
        //then
        assertEquals(savedComment, getComment);
    }

    @Test
    void update() throws Exception {
        //given
        Comment savedComment = commentService.createComment(commentDto);

        //when
        Comment updatedComment = commentService.updateComment(savedComment.getId(),
                new UpdateCommentRequestDto(user1.getId(), "updatedCommentContent"));

        //then
        assertEquals(updatedComment.getContent().getContentText(), "updatedCommentContent");
    }

    @Test
    void like() throws Exception {
        //given
        Comment savedComment = commentService.createComment(commentDto);
        LikeCommentRequestDto likeDto = new LikeCommentRequestDto(user2.getId());
        //when
        commentService.likeComment(savedComment.getId(), likeDto);

        //then
        assertEquals(savedComment.getLikeCount().getCount(), 1);
    }

    @Test
    void like_already_Ex() throws Exception {
        //given
        Comment savedComment = commentService.createComment(commentDto);
        LikeCommentRequestDto likeDto = new LikeCommentRequestDto(user2.getId()
        );

        commentService.likeComment(savedComment.getId(), likeDto);
        //when
        commentService.likeComment(savedComment.getId(), likeDto);
        //then
        assertEquals(savedComment.getLikeCount().getCount(), 1);

    }


    @Test
    void unlike() throws Exception {
        //given
        Comment savedComment = commentService.createComment(commentDto);
        LikeCommentRequestDto likeDto = new LikeCommentRequestDto(user2.getId());
        commentService.likeComment(savedComment.getId(),likeDto);

        //when
        commentService.unlikeComment(savedComment.getId(),likeDto);

        //then
        assertEquals(savedComment.getLikeCount().getCount(), 0);

        commentService.unlikeComment(savedComment.getId(), likeDto);
        assertEquals(savedComment.getLikeCount().getCount(), 0);
    }

}