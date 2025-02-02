package fastcampus.feed.post.repository.post_queue;

import fastcampus.feed.post.repository.entity.post.PostEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserPostQueueRedisRepositoryImpl implements UserPostQueueRedisRepository{

    @Override
    public void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList) {

    }

    @Override
    public void publishPostListToFollowerUser(List<PostEntity> postEntity, Long userId) {

    }

    @Override
    public void deleteFeed(Long userId, Long authorId) {

    }
}
