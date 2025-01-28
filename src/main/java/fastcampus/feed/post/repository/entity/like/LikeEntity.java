package fastcampus.feed.post.repository.entity.like;

import fastcampus.feed.common.repository.entity.TimeBaseEntity;
import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.domain.comment.Comment;
import fastcampus.feed.user.domain.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "likes")
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeIdEntity likeIdEntity;


    public LikeEntity(Post post, User user){
        this.likeIdEntity = new LikeIdEntity(post.getId(), user.getId(), LikeTarget.POST);
    }

    public LikeEntity(Comment comment, User user){
        this.likeIdEntity = new LikeIdEntity(comment.getId(), user.getId(), LikeTarget.COMMENT);
    }
}
