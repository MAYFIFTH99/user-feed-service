package fastcampus.feed.post.repository.interfaces;

import fastcampus.feed.post.controller.dto.GetCommentContentResponseDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentQueryRepository {

    /**
     +     * 사용자별 게시물의 댓글 목록을 조회합니다.
     +     *
     +     * @param userId 조회하는 사용자의 ID
     +     * @param postId 댓글이 속한 게시물의 ID
     +     * @param lastContentId 페이지네이션을 위한 마지막 컨텐츠 ID
     +     * @return 댓글 목록
     +     */
    List<GetCommentContentResponseDto> getCommentList(Long postId, Long userId, Long lastContentId);
}
