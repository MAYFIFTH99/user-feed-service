//package fastcampus.feed.post.repository.post_queue;
//
//import com.querydsl.core.types.Projections;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import fastcampus.feed.post.controller.dto.GetPostContentResponseDto;
//import fastcampus.feed.post.repository.entity.like.LikeTarget;
//import fastcampus.feed.post.repository.entity.like.QLikeEntity;
//import fastcampus.feed.post.repository.entity.post.QPostEntity;
//import fastcampus.feed.post.repository.entity.post.QUserPostQueueEntity;
//import fastcampus.feed.user.repository.entity.QUserEntity;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//@Repository
//@RequiredArgsConstructor
//public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {
//
//    private final JPAQueryFactory queryFactory;
//    private static final QUserPostQueueEntity userPostQueueEntity = QUserPostQueueEntity.userPostQueueEntity;
//    private static final QPostEntity postEntity = QPostEntity.postEntity;
//    private static final QUserEntity userEntity = QUserEntity.userEntity;
//    private static final QLikeEntity likeEntity = QLikeEntity.likeEntity;
//
//
//    @Override
//    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
//        return queryFactory
//                .select(
//                        Projections.fields(
//                                GetPostContentResponseDto.class,
//                                postEntity.id.as("postId"),
//                                postEntity.content.as("content"),
//                                userEntity.id.as("userId"),
//                                userEntity.name.as("userName"),
//                                userEntity.profileImageUrl.as("userProfileImage"),
//                                postEntity.regDt.as("createdAt"),
//                                postEntity.updDt.as("updatedAt"),
//                                postEntity.commentCount.as("commentCount"),
//                                postEntity.likeCount.as("likeCount"),
//                                likeEntity.isNotNull().as("isLikedByMe")
//                        )
//                )
//                .from(userPostQueueEntity)
//                .join(postEntity).on(userPostQueueEntity.postId.eq(postEntity.id))
//                .join(userEntity).on(userPostQueueEntity.authorId.eq(userEntity.id))
//                .leftJoin(likeEntity).on(hasLike(userId))
//                .where(
//                        userPostQueueEntity.userId.eq(userId),
//                        hasLastData(lastPostId)
//                )
//                .orderBy(userPostQueueEntity.postId.desc())
//                .limit(20)
//                .fetch();
//    }
//
//    private BooleanExpression hasLastData(Long lastId) {
//        if (lastId == null) {
//            return null;
//        }
//
//        return postEntity.id.lt(lastId);
//    }
//
//    private BooleanExpression hasLike(Long userId) {
//        if (userId == null) {
//            return null;
//        }
//
//        return postEntity.id
//                .eq(likeEntity.likeIdEntity.targetId)
//                .and(likeEntity.likeIdEntity.targetType.eq(LikeTarget.valueOf("POST")))
//                .and(likeEntity.likeIdEntity.userId.eq(userId));
//    }
//
//}
