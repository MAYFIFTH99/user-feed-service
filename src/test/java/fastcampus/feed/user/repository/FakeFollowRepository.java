package fastcampus.feed.user.repository;

import fastcampus.feed.user.domain.User;
import java.util.HashSet;
import java.util.Set;

public class FakeFollowRepository implements FollowRepository {

    Set<Follow> follows = new HashSet<>();

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        return follows.contains(new Follow(user.getId(), targetUser.getId()));
    }

    @Override
    public void save(User user, User targetUser) {
        follows.add(new Follow(user.getId(), targetUser.getId()));
    }

    @Override
    public void delete(User user, User targetUser) {
        follows.remove(new Follow(user.getId(), targetUser.getId()));
    }


}

record Follow(Long userId, Long targetUserId) {

}
