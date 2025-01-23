package fastcampus.feed.user.repository;

import fastcampus.feed.user.domain.User;

public interface FollowRepository {
    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser);
    void delete(User user, User targetUser);

}
