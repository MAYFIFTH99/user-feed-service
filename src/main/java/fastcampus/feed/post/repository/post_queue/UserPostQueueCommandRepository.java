package fastcampus.feed.post.repository.post_queue;

import fastcampus.feed.post.repository.entity.post.PostEntity;

public interface UserPostQueueCommandRepository {
    void publishPost(PostEntity postEntity); // 내 팔로워들한테 보여주는 메서드
    void saveFollowPost(Long userId, Long targetId); // 내가 팔로우하는 사람들의 피드를 보여주는 메서드
    void deleteUnFollowPost(Long userId, Long targetId); // 내가 팔로우하는 사람들의 피드를 삭제하는 메서드
}
