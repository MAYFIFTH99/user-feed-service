package fastcampus.feed.post.service.dto;

import fastcampus.feed.post.domain.PostPublishState;

public record UpdatePostRequestDto(Long userId,String content, PostPublishState publishState) {

}
