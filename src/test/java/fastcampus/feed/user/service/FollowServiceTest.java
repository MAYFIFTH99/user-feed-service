package fastcampus.feed.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.repository.FakeFollowRepository;
import fastcampus.feed.user.repository.FakeUserRepository;
import fastcampus.feed.user.repository.FollowRepository;
import fastcampus.feed.user.repository.UserRepository;
import fastcampus.feed.user.service.dto.CreateUserRequestDto;
import fastcampus.feed.user.service.dto.FollowUserRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FollowServiceTest {

    FollowRepository followRepository = new FakeFollowRepository();
    UserRepository fakeUserRepository = new FakeUserRepository();
    UserService userService = new UserService(fakeUserRepository);
    FollowService followService = new FollowService(followRepository, userService);


    User user1;
    User user2;

    @BeforeEach
    void setUp() {
        CreateUserRequestDto dto1 = new CreateUserRequestDto("test1", "");
        CreateUserRequestDto dto2 = new CreateUserRequestDto("test2", "");
        user1 = userService.createUser(dto1);
        user2 = userService.createUser(dto2);
    }

    @Test
    void follow_정상() throws Exception {
        //given
        FollowUserRequestDto dto = new FollowUserRequestDto(user1.getId(), user2.getId());
        //when
        followService.follow(dto);
        //then
        assertThat(user1.getFollowingCount()).isEqualTo(1);
        assertThat(user1.getFollowerCount()).isEqualTo(0);
        assertThat(user2.getFollowingCount()).isEqualTo(0);
        assertThat(user2.getFollowerCount()).isEqualTo(1);
    }

    @Test
    void follow_이미_follow() throws Exception {
        //given
        FollowUserRequestDto dto = new FollowUserRequestDto(user1.getId(),
                user2.getId());
        followService.follow(dto);
        //when & then
        assertThrows(IllegalStateException.class, () -> followService.follow(dto));
    }

    @Test
    void follow_자기자신() throws Exception {
        //given
        FollowUserRequestDto dto = new FollowUserRequestDto(user1.getId(),
                user1.getId());
        //when & then
        assertThrows(IllegalStateException.class, () -> followService.follow(dto));
    }

    @Test
    void unfollow_정상() throws Exception {
        //given
        FollowUserRequestDto dto = new FollowUserRequestDto(user1.getId(),
                user2.getId());
        followService.follow(dto);

        //when
        followService.unfollow(dto);

        //then
        assertThat(user1.getFollowingCount()).isEqualTo(0);
        assertThat(user1.getFollowerCount()).isEqualTo(0);
        assertThat(user2.getFollowingCount()).isEqualTo(0);
        assertThat(user2.getFollowerCount()).isEqualTo(0);
    }

    @Test
    void unfollow_자기자신() throws Exception {
        //given
        FollowUserRequestDto dto = new FollowUserRequestDto(user1.getId(),
                user1.getId());

        //when & then
        assertThrows(IllegalStateException.class, () -> followService.unfollow(dto));
    }
}