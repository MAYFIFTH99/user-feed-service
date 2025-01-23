package fastcampus.feed.post.common;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class DateTimeInfo {
    private boolean isEdited;
    private LocalDateTime dateTime;

    public DateTimeInfo() {
        this.isEdited = false;
        this.dateTime = LocalDateTime.now();
    }

    public void update(){
        this.isEdited = true;
        this.dateTime = LocalDateTime.now();
    }
}
