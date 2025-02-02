package fastcampus.feed.user.service;

import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.domain.UserInfo;
import fastcampus.feed.user.repository.UserRepository;
import fastcampus.feed.user.service.dto.CreateUserRequestDto;
import fastcampus.feed.user.service.dto.GetUserResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo userInfo = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, userInfo);
        return userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findById(id);
    }

    public GetUserResponseDto getUserResponseDto(Long id){
        User user = getUser(id);
        return new GetUserResponseDto(user);
    }

}
