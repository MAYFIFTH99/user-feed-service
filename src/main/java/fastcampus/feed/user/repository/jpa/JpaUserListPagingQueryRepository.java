package fastcampus.feed.user.repository.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fastcampus.feed.user.repository.entity.QFollowEntity;
import fastcampus.feed.user.repository.entity.QUserEntity;
import fastcampus.feed.user.service.dto.GetUserListResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

    private final JPAQueryFactory queryFactory;

    private final QUserEntity userEntity = QUserEntity.userEntity;
    private final QFollowEntity followEntity = QFollowEntity.followEntity;


    public List<GetUserListResponseDto> getFollowerList(Long userId, Long lastFollowingId){
        return queryFactory
                .select(Projections
                        .constructor(
                                GetUserListResponseDto.class,
                                userEntity.name,
                                userEntity.profileImageUrl))
                .from(followEntity)
                .join(userEntity)
                .on(followEntity.followingId.eq(userEntity.id))
                .where(followEntity.followerId.eq(userId),
                        hasLastData(lastFollowingId)
                        )
                .orderBy(userEntity.id.desc())
                .limit(20)
                .fetch();

    }

    private BooleanExpression hasLastData(Long lastId){
        if(lastId == null) return null;

        return userEntity.id.lt(lastId);
    }


}
