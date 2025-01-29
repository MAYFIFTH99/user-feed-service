package fastcampus.feed.user.controller;

import fastcampus.feed.common.controller.Response;
import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.repository.jpa.JpaUserListQueryRepository;
import fastcampus.feed.user.service.UserService;
import fastcampus.feed.user.service.dto.CreateUserRequestDto;
import fastcampus.feed.user.service.dto.GetUserListResponseDto;
import fastcampus.feed.user.service.dto.GetUserResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JpaUserListQueryRepository jpaUserListQueryRepository;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}/follow")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable Long userId) {
        return Response.ok(jpaUserListQueryRepository.getFollowerUserListByUserId(userId));
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable Long userId) {
        return Response.ok(jpaUserListQueryRepository.getFollowingUserListByUserId(userId));
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable Long userId) {
        return Response.ok(userService.getUserResponseDto(userId));
    }



}
