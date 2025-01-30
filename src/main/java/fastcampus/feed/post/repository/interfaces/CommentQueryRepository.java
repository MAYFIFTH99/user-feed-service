package fastcampus.feed.post.repository.interfaces;

import fastcampus.feed.post.controller.dto.GetCommentContentResponseDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentQueryRepository {

    List<GetCommentContentResponseDto> getCommentList(Long postId,Long userId, Long lastContentId);
}
