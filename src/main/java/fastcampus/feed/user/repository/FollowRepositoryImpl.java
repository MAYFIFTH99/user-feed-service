package fastcampus.feed.user.repository;

import fastcampus.feed.post.repository.post_queue.UserPostQueueCommandRepository;
import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.repository.entity.FollowEntity;
import fastcampus.feed.user.repository.entity.FollowIdEntity;
import fastcampus.feed.user.repository.entity.UserEntity;
import fastcampus.feed.user.repository.jpa.JpaFollowRepository;
import fastcampus.feed.user.repository.jpa.JpaUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepository {

    private final JpaFollowRepository jpaFollowRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;


    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        FollowIdEntity followIdEntity = new FollowIdEntity(user.getId(), targetUser.getId());
        return jpaFollowRepository.existsById(followIdEntity);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        FollowEntity followEntity = new FollowEntity(user.getId(), targetUser.getId());
        jpaFollowRepository.save(followEntity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        userPostQueueCommandRepository.saveFollowPost(user.getId(), targetUser.getId());
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        FollowIdEntity followIdEntity = new FollowIdEntity(user.getId(), targetUser.getId());
        jpaFollowRepository.deleteById(followIdEntity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        userPostQueueCommandRepository.deleteUnFollowPost(user.getId(), targetUser.getId());
    }
}
