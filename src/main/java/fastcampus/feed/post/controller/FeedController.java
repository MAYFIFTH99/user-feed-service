package fastcampus.feed.post.controller;

import fastcampus.feed.common.controller.Response;
import fastcampus.feed.post.controller.dto.GetPostContentResponseDto;
import fastcampus.feed.post.repository.post_queue.UserPostQueueQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository queueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@PathVariable Long userId, Long lastPostId) {
        List<GetPostContentResponseDto> result = queueQueryRepository.getContentResponse(userId, lastPostId);
        return Response.ok(result);
    }
}
