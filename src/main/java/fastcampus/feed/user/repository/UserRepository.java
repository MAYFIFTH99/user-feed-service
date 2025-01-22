package fastcampus.feed.user.repository;

import fastcampus.feed.user.domain.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long userId);

}
