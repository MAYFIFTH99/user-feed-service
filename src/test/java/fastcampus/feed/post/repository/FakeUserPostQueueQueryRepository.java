package fastcampus.feed.post.repository;

import fastcampus.feed.post.controller.dto.GetPostContentResponseDto;
import fastcampus.feed.post.repository.entity.post.PostEntity;
import fastcampus.feed.post.repository.post_queue.UserPostQueueQueryRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class FakeUserPostQueueQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserPostQueueRedisRepository fakeUserPostQueueRedisRepository;

    public FakeUserPostQueueQueryRepository(
            FakeUserPostQueueRedisRepository fakeUserPostQueueRedisRepository) {
        this.fakeUserPostQueueRedisRepository = fakeUserPostQueueRedisRepository;
    }

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        List<PostEntity> postEntities = fakeUserPostQueueRedisRepository.getPostsByUserId(userId);
        List<GetPostContentResponseDto> result = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            GetPostContentResponseDto res = GetPostContentResponseDto.builder()
                    .id(postEntity.getId())
                    .build();
            result.add(res);
        }
        return result;
    }
}
