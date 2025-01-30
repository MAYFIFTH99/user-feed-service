package fastcampus.feed.post.repository.entity.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_post_queue")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPostQueueEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long postId;
    private Long authorId;

    public UserPostQueueEntity(Long userId, Long postId, Long authorId){
        this.userId = userId;
        this.postId = postId;
        this.authorId = authorId;
    }

}
