package fastcampus.feed.user.repository;

import fastcampus.feed.user.domain.User;

public interface UserRepository  {
    User save(User user);
    User findById(Long userId);
}
