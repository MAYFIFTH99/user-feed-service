package fastcampus.feed.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fastcampus.feed.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserRepository  {
    User save(User user);
    User findById(Long userId);
}
