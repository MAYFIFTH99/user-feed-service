package fastcampus.feed.post.repository.post_queue;

import fastcampus.feed.post.repository.entity.post.PostEntity;
import fastcampus.feed.post.repository.jpa.JpaPostRepository;
import fastcampus.feed.user.repository.entity.UserEntity;
import fastcampus.feed.user.repository.jpa.JpaFollowRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository{

    private final JpaPostRepository jpaPostRepository;
    private final JpaFollowRepository jpaFollowRepository;
//    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;
    private final UserPostQueueRedisRepository redisRepository;

    @Transactional
    @Override// 나를 팔로우하는 사람들에게 피드를 보여주는 메서드
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> followerIds = jpaFollowRepository.findFollowers(userEntity.getId());

//        List<UserPostQueueEntity> userPostQueueEntityList = followerIds.stream()
//                        .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
//                        .toList();
//
//        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
        redisRepository.publishPostToFollowingUserList(postEntity, followerIds);
    }

    @Transactional
    @Override// 내가 팔로우하는 사람들의 피드를 보여주는 메서드
    public void saveFollowPost(Long userId, Long targetId) {
//        List<Long> postIds = jpaPostRepository.findAllPostIdsByAuthorId(targetId);
        List<PostEntity> posts = jpaPostRepository.findAllPostByAuthorId(targetId);
//        List<UserPostQueueEntity> userPostQueueEntityList = postIds.stream()
//                .map(postId -> new UserPostQueueEntity(userId, postId, targetId))
//                .toList();
//
//        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
        redisRepository.publishPostListToFollowerUser(posts, userId);
    }

    @Transactional
    @Override// 언팔로우 시 팔로우한 사람의 피드를 삭제하는 메서드
    public void deleteUnFollowPost(Long userId, Long targetId) {
//        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
        redisRepository.deleteFeed(userId, targetId);
    }
}
