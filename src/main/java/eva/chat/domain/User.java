package eva.chat.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String nickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @MapKey(name = "chatWith")
    private Map<String, Chat> chats = new HashMap<>();

    public User() {
    }

    public boolean addChat(Chat chat){
        if (this.chats == null){
            this.chats = new HashMap<>();
            return true;
        }else if (!this.chats.containsKey(chat.getChatWith())){
            this.chats.put(chat.getChatWith(), chat);
            return true;
        }
        return false;
    }


}
