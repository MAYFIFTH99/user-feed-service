package fastcampus.feed.post.domain.content;

import fastcampus.feed.post.common.DateTimeInfo;
import lombok.Getter;

@Getter
public abstract class Content {

    protected String contentText;
    private final DateTimeInfo dateTime;


    public Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.dateTime = new DateTimeInfo();
    }

    abstract protected void checkText(String content);

    public void updateContent(String content){
        checkText(content);
        dateTime.update();
        this.contentText = content;
    }
}
