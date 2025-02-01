package fastcampus.feed.acceptance.steps.utils;

import static fastcampus.feed.acceptance.steps.UserAcceptanceSteps.createUser;
import static fastcampus.feed.acceptance.steps.UserAcceptanceSteps.followUser;

import fastcampus.feed.user.service.dto.CreateUserRequestDto;
import fastcampus.feed.user.service.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    public void loadData(){
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        createUser(dto);
        createUser(dto);
        createUser(dto);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }

}
