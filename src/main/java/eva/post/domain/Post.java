package eva.post.domain;

import eva.chat.domain.Chat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    private final String content;

    private final String timestamp;

    private final String type;

    public Post() {
        this.content = "";
        this.timestamp = "";
        this.type = "";
    }

    public Post(String content, String timestamp, Chat chat, String type){
        this.content = content;
        this.timestamp = timestamp;
        this.type = type;
    }
}
