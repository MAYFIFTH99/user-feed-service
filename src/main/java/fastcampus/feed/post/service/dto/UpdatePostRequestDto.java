package fastcampus.feed.post.service.dto;

import fastcampus.feed.post.domain.PostPublishState;

public record UpdatePostRequestDto(Long userId, Long postId, String content, PostPublishState publishState) {

}
