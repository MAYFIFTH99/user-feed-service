package fastcampus.feed.user.repository.entity;

import fastcampus.feed.common.repository.entity.TimeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "follow")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FollowIdEntity.class)
public class FollowEntity extends TimeBaseEntity {


    @Id
    private Long followingId;

    @Id
    private Long followerId;


}
