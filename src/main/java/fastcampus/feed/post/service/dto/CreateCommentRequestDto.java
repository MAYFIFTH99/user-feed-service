package fastcampus.feed.post.service.dto;

public record CreateCommentRequestDto(Long userId, Long postId, String content) {

}
