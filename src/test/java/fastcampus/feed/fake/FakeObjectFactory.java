package fastcampus.feed.fake;

import fastcampus.feed.post.repository.FakeCommentRepository;
import fastcampus.feed.post.repository.FakeLikeRepository;
import fastcampus.feed.post.repository.FakePostRepository;
import fastcampus.feed.post.repository.interfaces.CommentRepository;
import fastcampus.feed.post.repository.interfaces.LikeRepository;
import fastcampus.feed.post.repository.interfaces.PostRepository;
import fastcampus.feed.post.service.CommentService;
import fastcampus.feed.post.service.PostService;
import fastcampus.feed.user.repository.FakeFollowRepository;
import fastcampus.feed.user.repository.FakeUserRepository;
import fastcampus.feed.user.repository.FollowRepository;
import fastcampus.feed.user.repository.UserRepository;
import fastcampus.feed.user.service.FollowService;
import fastcampus.feed.user.service.UserService;

public class FakeObjectFactory {

    private FakeObjectFactory() {
    }

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final FollowRepository fakeUserRelationRepository = new FakeFollowRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final FollowService followService = new FollowService(
            fakeUserRelationRepository, userService);
    private static final PostService postService = new PostService(userService, fakePostRepository,
            fakeLikeRepository);
    private static final CommentService commentService = new CommentService(fakeCommentRepository,
            userService, fakeLikeRepository, postService);

    public static UserService getUserService() {
        return userService;
    }

    public static FollowService getFollowService() {
        return followService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }

}
