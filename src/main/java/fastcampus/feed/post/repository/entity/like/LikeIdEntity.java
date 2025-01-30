package fastcampus.feed.post.repository.entity.like;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class LikeIdEntity {

    private Long targetId;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private LikeTarget targetType;
}
