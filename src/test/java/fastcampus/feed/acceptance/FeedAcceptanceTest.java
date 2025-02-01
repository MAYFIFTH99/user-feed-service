package fastcampus.feed.acceptance;

import static fastcampus.feed.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static fastcampus.feed.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static org.assertj.core.api.Assertions.assertThat;

import fastcampus.feed.acceptance.steps.utils.AcceptanceTestTemplate;
import fastcampus.feed.post.controller.dto.GetPostContentResponseDto;
import fastcampus.feed.post.domain.PostPublishState;
import fastcampus.feed.post.service.dto.CreatePostRequestDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {


    /**
     * User1 --- follow --- User2
     * User1 --- follow --- User3
     */
    @BeforeEach
    void setUp(){
        super.init();
    }

    /**
     * User2 --- Create Post --- postId = 1 and then User1 --- Get Post 1 --- From Feed
     */
    @Test
    @Transactional
    void 팔로우하는_유저의_피드보기() {
        //given
        CreatePostRequestDto postDto = new CreatePostRequestDto(2L, "user2 post",
                PostPublishState.PUBLIC);

        Long createdPostId = requestCreatePost(postDto);

        //when
        List<GetPostContentResponseDto> result = requestFeed(1L);

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(createdPostId).isEqualTo(1L);
        assertThat(result.get(0).getContent()).isEqualTo("user2 post");
    }
}
