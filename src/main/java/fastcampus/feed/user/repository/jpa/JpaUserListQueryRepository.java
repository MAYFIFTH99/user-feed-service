package fastcampus.feed.user.repository.jpa;

import fastcampus.feed.user.repository.entity.UserEntity;
import fastcampus.feed.user.service.dto.GetUserListResponseDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT new fastcampus.feed.user.service.dto.GetUserListResponseDto(u.name, u.profileImageUrl) "
            + "FROM UserEntity u "
            + "JOIN FollowEntity f "
            + "ON u.id = f.followingId "
            + "WHERE u.id = :userId")
    List<GetUserListResponseDto> getFollowingUserListByUserId(@Param("userId") Long userId);

    @Query("SELECT new fastcampus.feed.user.service.dto.GetUserListResponseDto(u.name, u.profileImageUrl) "
            + "FROM UserEntity u "
            + "JOIN FollowEntity f "
            + "ON u.id = f.followerId "
            + "WHERE u.id = :userId")
    List<GetUserListResponseDto> getFollowerUserListByUserId(@Param("userId") Long userId);

}
