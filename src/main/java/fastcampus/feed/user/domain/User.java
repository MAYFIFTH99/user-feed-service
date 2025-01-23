package fastcampus.feed.user.domain;


import fastcampus.feed.common.domain.PositiveIntegerCount;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
public class User {

    private final Long id;
    private final UserInfo userInfo; // VO 객체를 이용하여 도메인 검증 분리

    private final PositiveIntegerCount followingCount;
    private final PositiveIntegerCount followerCount;


    public User(Long id, UserInfo userInfo) {
        this.id = id;
        this.userInfo = userInfo;

        this.followingCount = new PositiveIntegerCount();
        this.followerCount = new PositiveIntegerCount();


    public void follow(User targetUser) {
        if(this.equals(targetUser)){
            throw new IllegalStateException();
        }
        followingCount.increase();
        targetUser.followerCountIncrease();
    }

    public void unfollow(User targetUser) {
        if(this.equals(targetUser)){
            throw new IllegalStateException();
        }
        followingCount.decrease();
        targetUser.followerCountDecrease();
    }

    private void followerCountIncrease(){
        followerCount.increase();
    }
    private void followerCountDecrease(){
        followerCount.decrease();
    }

}
