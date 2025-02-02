package fastcampus.feed.user.repository;

import fastcampus.feed.user.domain.User;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class FakeUserRepository implements UserRepository{
    private final static Map<Long, User> store = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public User save(User user) {
        long id = sequence.getAndIncrement();
        User newUser = new User(id, user.getUserInfo());
        store.put(id, newUser);
        return newUser;
    }

    @Override
    public User findById(Long userId) {
        if (!store.containsKey(userId)) {
            throw new IllegalArgumentException("User를 찾을 수 없습니다.");
        }
        return store.get(userId);
    }
}
