package fastcampus.feed.user.repository.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FollowIdEntity  {

    private Long followingId;
    private Long followerId;
}
