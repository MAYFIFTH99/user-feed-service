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

    /**
     * 좋아요 대상(게시물 또는 댓글)의 ID
     */
    private Long targetId;

    /**
     * 좋아요를 누른 사용자의 ID
     */
    private Long userId;

    /**
     * 좋아요 대상의 유형(Type - Post, Comment)
     */
    @Enumerated(EnumType.STRING)
    private LikeTarget targetType;
}
