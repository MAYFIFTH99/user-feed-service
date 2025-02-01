package fastcampus.feed.post.repository.post_queue;

import fastcampus.feed.post.repository.entity.post.PostEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostQueueRedisRepository {

    void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList);
    void publishPostListToFollowerUser(List<PostEntity> postEntity, Long userId);
    void deleteFeed(Long userId, Long authorId);
}
