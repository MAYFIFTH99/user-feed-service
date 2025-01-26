package fastcampus.feed.post.service;

import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.domain.content.PostContent;
import fastcampus.feed.post.repository.interfaces.LikeRepository;
import fastcampus.feed.post.repository.interfaces.PostRepository;
import fastcampus.feed.post.service.dto.LikePostRequestDto;
import fastcampus.feed.post.service.dto.CreatePostRequestDto;
import fastcampus.feed.post.service.dto.UpdatePostRequestDto;
import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.service.UserService;
import org.springframework.transaction.annotation.Transactional;

public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private LikeRepository likeRepository;

    public PostService(PostRepository postRepository, UserService userService,
            LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeRepository = likeRepository;
    }

    @Transactional
    public Post getPost(Long id){
        return postRepository.findById(id);
    }

    @Transactional
    public void createPost(CreatePostRequestDto dto) {
        User user = userService.getUser(dto.userId());

        PostContent postContent = new PostContent(dto.postContent());
        Post post = new Post(null, user, postContent, dto.postPublishState());

        postRepository.save(post);

    }

    @Transactional
    public void updatePost(UpdatePostRequestDto dto){
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());
        post.updatePost(user, dto.content(), dto.publishState());
        postRepository.save(post);
    }

    @Transactional
    public void likePost(LikePostRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = getPost(dto.postId());

        if (likeRepository.isAlreadyLiked(user, post)) {
            return;
        }

        post.like(user);
        likeRepository.save(user, post);
    }

    @Transactional
    public void unlikePost(LikePostRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = getPost(dto.postId());

        if (likeRepository.isAlreadyLiked(user, post)) {
            likeRepository.delete(user, post);
        }

        post.unlike(user);

    }


}
