package fastcampus.feed.user.repository.jpa;

import fastcampus.feed.user.repository.entity.FollowEntity;
import fastcampus.feed.user.repository.entity.FollowIdEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaFollowRepository extends JpaRepository<FollowEntity, FollowIdEntity> {

    @Query("SELECT f.followingId FROM FollowEntity f WHERE f.followerId = :userId")
    List<Long> findFollowers(Long userId);
}
