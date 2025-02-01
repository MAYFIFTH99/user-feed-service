package fastcampus.feed.user.domain;

public class UserInfo {
    private String name;
    private String profileImageUrl;

    public UserInfo(String name, String profileImageUrl) {
        if(name == null){
            throw new IllegalArgumentException("사용자 이름은 null 일 수 없습니다.");
        }

        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("사용자 이름은 빈 문자열일 수 없습니다.");
        }

        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
