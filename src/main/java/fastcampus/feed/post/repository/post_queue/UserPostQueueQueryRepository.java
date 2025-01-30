package fastcampus.feed.post.repository.post_queue;

import fastcampus.feed.post.controller.dto.GetPostContentResponseDto;
import java.util.List;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId);

}
