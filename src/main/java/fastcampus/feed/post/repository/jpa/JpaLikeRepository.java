package fastcampus.feed.post.repository.jpa;

import fastcampus.feed.post.repository.entity.like.LikeEntity;
import fastcampus.feed.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {

}
