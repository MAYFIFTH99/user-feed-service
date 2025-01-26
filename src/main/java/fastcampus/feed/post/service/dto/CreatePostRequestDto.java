package fastcampus.feed.post.service.dto;

import fastcampus.feed.post.domain.PostPublishState;

public record CreatePostRequestDto(Long userId, String postContent, PostPublishState postPublishState) {
}
