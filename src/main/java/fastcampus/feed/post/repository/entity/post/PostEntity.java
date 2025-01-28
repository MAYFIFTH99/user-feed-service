package fastcampus.feed.post.repository.entity.post;

import fastcampus.feed.common.domain.PositiveIntegerCount;
import fastcampus.feed.common.repository.entity.TimeBaseEntity;
import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.domain.PostPublishState;
import fastcampus.feed.post.domain.content.PostContent;
import fastcampus.feed.user.repository.entity.UserEntity;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    private String content;

    @Convert(converter = PostPublishStateConverter.class)
    private PostPublishState postPublishState;

    private Integer likeCount;

    public PostEntity(Post post){
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getPostContent().getContentText();
        this.postPublishState = post.getPostPublishState();
        this.likeCount = post.getLikeCount().getCount();
    }

    public Post toPost(){
        return Post.builder()
                .id(id)
                .author(author.toUser())
                .postContent(new PostContent(content))
                .postPublishState(postPublishState)
                .likeCount(new PositiveIntegerCount(likeCount))
                .build();
    }
}
