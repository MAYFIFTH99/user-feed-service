package fastcampus.feed.user.repository;

import fastcampus.feed.user.domain.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeUserRepository implements UserRepository{
    static Map<Long, User> store = new HashMap<>();
    Long sequence = 1L;

    @Override
    public User save(User user) {
        long id = sequence++;

        User newUser = new User(id, user.getUserInfo());
        store.put(id, newUser);
        return newUser;
    }

    @Override
    public User findById(Long userId) {
        return store.get(userId);
    }
}
