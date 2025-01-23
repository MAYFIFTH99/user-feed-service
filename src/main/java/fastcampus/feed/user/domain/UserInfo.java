package fastcampus.feed.user.domain;

public class UserInfo {
    private String name;
    private String profileImageUrl;

    public UserInfo(String name, String profileImageUrl) {
        if(name == null){
            throw new IllegalStateException();
        }
        if (name.isEmpty()) {
            throw new IllegalStateException();
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
