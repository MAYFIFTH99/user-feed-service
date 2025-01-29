package fastcampus.feed.user.repository;

import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.repository.entity.UserEntity;
import fastcampus.feed.user.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        UserEntity saveEntity = jpaUserRepository.save(entity);
        return saveEntity.toUser();
    }

    @Override
    public User findById(Long userId) {
        return jpaUserRepository.findById(userId).map(UserEntity::toUser)
                .orElseThrow(IllegalStateException::new);
    }
}
