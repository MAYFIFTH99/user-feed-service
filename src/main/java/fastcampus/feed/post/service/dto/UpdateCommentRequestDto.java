package fastcampus.feed.post.service.dto;

public record UpdateCommentRequestDto(Long userId, Long commentId, String content) {

}
