package eva.chat.domain;

import eva.post.domain.Post;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Chat {

    private String chatWith;

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> postList;

    private int newPosts;

    public Chat() {
    }

    public Chat(String chatWith, User user) {
        this.postList = new ArrayList<>();
        this.chatWith = chatWith;
        this.user = user;

    }

    public void addPost(Post post){
        this.postList.add(post);
    }

    public void addNewPosts(){
        this.newPosts++;
    }

    public void resetNewPosts(){
        this.newPosts = 0;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatWith='" + chatWith + '\'' +
                ", id=" + id +
                ", user=" + user.getNickname() +
                '}';
    }
}
