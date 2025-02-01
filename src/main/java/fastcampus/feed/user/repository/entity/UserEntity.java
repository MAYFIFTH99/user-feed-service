package fastcampus.feed.user.repository.entity;

import fastcampus.feed.common.domain.PositiveIntegerCount;
import fastcampus.feed.common.repository.entity.TimeBaseEntity;
import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.domain.UserInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicUpdate
@Getter
public class UserEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String profileImageUrl;
    private int followerCount;
    private int followingCount;

    public UserEntity(User user){
        this.id = user.getId();
        this.name = user.getUserInfo().getName();
        this.profileImageUrl = user.getUserInfo().getProfileImageUrl();
        this.followerCount = user.getFollowerCount();
        this.followingCount = user.getFollowingCount();
    }

    public User toUser(){
        return User.builder()
                .id(id)
                .userInfo(new UserInfo(name, profileImageUrl))
                .followerCount(new PositiveIntegerCount(followerCount))
                .followingCount(new PositiveIntegerCount(followingCount))
                .build();
    }

}
