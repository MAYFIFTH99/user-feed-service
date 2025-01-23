package fastcampus.feed.user.service.dto;

import fastcampus.feed.user.domain.User;

public record FollowUserRequestDto(Long userId, Long targetUserId) {

}
