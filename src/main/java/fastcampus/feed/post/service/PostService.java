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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final LikeRepository likeRepository;


    @Transactional
    public Post getPost(Long id){
        return postRepository.findById(id);
    }

    @Transactional
    public Post createPost(CreatePostRequestDto dto) {
        User user = userService.getUser(dto.userId());

        PostContent postContent = new PostContent(dto.postContent());
        Post post = new Post(null, user, postContent, dto.postPublishState());

        return postRepository.save(post);

    }

    @Transactional
    public void updatePost(Long postId, UpdatePostRequestDto dto){
        Post post = getPost(postId);
        User user = userService.getUser(dto.userId());
        post.updatePost(user, dto.content(), dto.publishState());
        postRepository.save(post);
    }

    @Transactional
    public void likePost(Long postId, LikePostRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = getPost(postId);

        if (likeRepository.isAlreadyLiked(user, post)) {
            return;
        }

        post.like(user);
        likeRepository.save(user, post);
    }

    @Transactional
    public void unlikePost(Long postId, LikePostRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = getPost(postId);

        if (likeRepository.isAlreadyLiked(user, post)) {
            post.unlike(user);
            likeRepository.delete(user, post);
        }
    }


}
