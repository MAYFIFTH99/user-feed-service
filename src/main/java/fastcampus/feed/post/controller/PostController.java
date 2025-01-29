package fastcampus.feed.post.controller;

import fastcampus.feed.common.controller.Response;
import fastcampus.feed.post.service.PostService;
import fastcampus.feed.post.service.dto.CreatePostRequestDto;
import fastcampus.feed.post.service.dto.LikePostRequestDto;
import fastcampus.feed.post.service.dto.UpdatePostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto){
        return Response.ok(postService.createPost(dto).getId());
    }

    @PatchMapping("/{postId}")
    public Response<Void> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequestDto dto){
        postService.updatePost(postId,dto);
        return Response.ok(null);
    }

    @PostMapping("/{postId}/like")
    public Response<Void> likePost(@PathVariable Long postId,@RequestBody LikePostRequestDto dto){
        postService.likePost(postId, dto);
        return Response.ok(null);
    }

    @PostMapping("/{postId}/unlike")
    public Response<Void> unlikePost(@PathVariable Long postId, @RequestBody LikePostRequestDto dto){
        postService.unlikePost(postId, dto);
        return Response.ok(null);
    }


}
