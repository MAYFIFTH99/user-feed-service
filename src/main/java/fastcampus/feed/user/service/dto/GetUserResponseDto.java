package fastcampus.feed.user.service.dto;

import fastcampus.feed.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImageUrl, int followingCount, int followerCount) {

    public GetUserResponseDto(User user){
        this(user.getId(), user.getUserInfo().getName(), user.getUserInfo().getProfileImageUrl(), user.getFollowingCount(), user.getFollowerCount());
    }
}
