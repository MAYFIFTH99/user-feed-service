package fastcampus.feed.post.service;

import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.domain.comment.Comment;
import fastcampus.feed.post.domain.content.CommentContent;
import fastcampus.feed.post.repository.interfaces.CommentRepository;
import fastcampus.feed.post.repository.interfaces.LikeRepository;
import fastcampus.feed.post.service.dto.CreateCommentRequestDto;
import fastcampus.feed.post.service.dto.LikeCommentRequestDto;
import fastcampus.feed.post.service.dto.UpdateCommentRequestDto;
import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());
        CommentContent commentContent = new CommentContent(dto.content());

        Comment comment = new Comment(null, user, post, commentContent);
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId);
    }

    @Transactional
    public Comment updateComment(Long commentId, UpdateCommentRequestDto dto){
        Comment comment = getComment(commentId);
        User user = userService.getUser(dto.userId());

        comment.updateComment(user, dto.content());

        return commentRepository.save(comment);
    }
    @Transactional
    public void likeComment(Long commentId, LikeCommentRequestDto dto) {
        Comment comment = getComment(commentId);
        User user = userService.getUser(dto.userId());

        if (likeRepository.isAlreadyLiked(user, comment)) {
            return;
        }

        comment.like(user);
        likeRepository.save(user, comment);
    }
    @Transactional
    public void unlikeComment(Long commentId, LikeCommentRequestDto dto) {
        Comment comment = getComment(commentId);
        User user = userService.getUser(dto.userId());

        if (likeRepository.isAlreadyLiked(user, comment)) {
            comment.unlike(user);
            likeRepository.delete(user, comment);
        }


    }
}
