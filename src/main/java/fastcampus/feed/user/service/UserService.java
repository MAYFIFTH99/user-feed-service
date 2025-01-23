package fastcampus.feed.user.service;

import fastcampus.feed.user.domain.User;
import fastcampus.feed.user.domain.UserInfo;
import fastcampus.feed.user.repository.UserRepository;
import fastcampus.feed.user.service.dto.CreateUserRequestDto;

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
        return userRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

}
