package fastcampus.feed.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fastcampus.feed.post.controller.dto.GetCommentContentResponseDto;
import fastcampus.feed.post.repository.entity.comment.QCommentEntity;
import fastcampus.feed.post.repository.entity.like.LikeTarget;
import fastcampus.feed.post.repository.entity.like.QLikeEntity;
import fastcampus.feed.post.repository.interfaces.CommentQueryRepository;
import fastcampus.feed.user.repository.entity.QUserEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QCommentEntity commentEntity = QCommentEntity.commentEntity;
    private final QUserEntity userEntity = QUserEntity.userEntity;
    private final QLikeEntity likeEntity = QLikeEntity.likeEntity;

    @Override
    public List<GetCommentContentResponseDto> getCommentList(Long postId,Long userId, Long lastContentId) {
        return queryFactory
                .select(
                        Projections.constructor(
                                GetCommentContentResponseDto.class,
                                commentEntity.id.as("id"),
                                commentEntity.content.as("content"),
                                userEntity.id.as("userId"),
                                userEntity.name.as("userName"),
                                userEntity.profileImageUrl.as("profileImageUrl"),
                                commentEntity.likeCount.as("likeCount"),
                                commentEntity.regDt.as("createdAt"),
                                commentEntity.updDt.as("updatedAt"),
                                likeEntity.isNotNull().as("isLikedByMe")
                        )
                )
                .from(commentEntity)
                .join(userEntity).on(commentEntity.author.id.eq(userEntity.id))
                .leftJoin(likeEntity).on(hasLike(userId))
                .where(commentEntity.post.id.eq(postId),
                        hasLastData(lastContentId)
                )
                .orderBy(commentEntity.id.desc())
                .limit(10)
                .fetch();
    }

    private BooleanExpression hasLastData(Long lastContentId) {
        if (lastContentId == null) {
            return null;
        }
        return commentEntity.id.lt(lastContentId);
    }

    private BooleanExpression hasLike(Long userId) {
        if (userId == null) {
            return null;
        }

        return commentEntity.id
                .eq(likeEntity.likeIdEntity.targetId)
                .and(likeEntity.likeIdEntity.userId.eq(userId))
                .and(likeEntity.likeIdEntity.targetType.eq(
                        LikeTarget.COMMENT));
    }
}
