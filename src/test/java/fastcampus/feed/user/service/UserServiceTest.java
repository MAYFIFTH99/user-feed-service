package fastcampus.feed.user.service;

import static org.junit.jupiter.api.Assertions.*;

import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.domain.UserInfo;
import fastcampus.feed.user.repository.FakeUserRepository;
import fastcampus.feed.user.repository.UserRepository;
import fastcampus.feed.user.service.dto.CreateUserRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    UserRepository userRepository = new FakeUserRepository();
    UserService userService = new UserService(userRepository);

    @Test
    void createUser() throws Exception {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test1", "");

        //when
        User savedUser = userService.createUser(dto);

        //then
        User findedUser = userService.getUser(savedUser.getId());
        Assertions.assertThat(savedUser.getId()).isEqualTo(findedUser.getId());
    }

    @Test
    void getUser() throws Exception {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test1", "");
        //when
        User user = userService.createUser(dto);
        User findUser = userService.getUser(user.getId());
        //then
        Assertions.assertThat(findUser.getId()).isEqualTo(user.getId());
        Assertions.assertThat(findUser.getUserInfo()).isEqualTo(user.getUserInfo());
        Assertions.assertThat(findUser.getUserInfo().getName()).isEqualTo(user.getUserInfo().getName());
        Assertions.assertThat(findUser.getUserInfo().getProfileImageUrl()).isEqualTo(user.getUserInfo().getProfileImageUrl());
    }

    @Test
    void getUser_예외() throws Exception {
        //given

        //when

        //then
    }

}