package fastcampus.feed.post.repository.entity.post;

import fastcampus.feed.post.domain.PostPublishState;
import jakarta.persistence.AttributeConverter;

public class PostPublishStateConverter implements AttributeConverter<PostPublishState, String> {

    @Override
    public String convertToDatabaseColumn(PostPublishState attribute) {
        return attribute.name();
    }

    @Override
    public PostPublishState convertToEntityAttribute(String dbData) {
        return PostPublishState.valueOf(dbData);
    }




}
