package fastcampus.feed.user.service;

import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.repository.FollowRepository;
import fastcampus.feed.user.repository.UserRepository;
import fastcampus.feed.user.service.dto.FollowUserRequestDto;

public class FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;

    public FollowService(FollowRepository followRepository, UserService userService) {
        this.followRepository = followRepository;
        this.userService = userService;
    }

    public void follow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (followRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalStateException();
        }
        user.follow(targetUser);
        followRepository.save(user, targetUser);
    }

    public void unfollow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (!followRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalStateException();
        }
        user.unfollow(targetUser);
        followRepository.delete(user, targetUser);
    }


}
