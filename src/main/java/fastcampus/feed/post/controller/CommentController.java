package fastcampus.feed.post.controller;

import fastcampus.feed.common.controller.Response;
import fastcampus.feed.post.service.CommentService;
import fastcampus.feed.post.service.dto.CreateCommentRequestDto;
import fastcampus.feed.post.service.dto.LikeCommentRequestDto;
import fastcampus.feed.post.service.dto.UpdateCommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto){
        return Response.ok(commentService.createComment(dto).getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Void> updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequestDto dto){
        commentService.updateComment(commentId, dto);
        return Response.ok(null);
    }

    @PostMapping("/{commentId}/like")
    public Response<Void> likeComment(@PathVariable Long commentId, @RequestBody LikeCommentRequestDto dto){
        commentService.likeComment(commentId, dto);
        return Response.ok(null);
    }

}
