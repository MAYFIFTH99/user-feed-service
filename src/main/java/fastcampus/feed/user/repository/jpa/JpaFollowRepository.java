package fastcampus.feed.user.repository.jpa;

import fastcampus.feed.user.repository.entity.FollowEntity;
import fastcampus.feed.user.repository.entity.FollowIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFollowRepository extends JpaRepository<FollowEntity, FollowIdEntity> {

}
