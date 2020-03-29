package eva.post.service;

import eva.chat.domain.Chat;
import eva.chat.domain.User;
import eva.chat.service.ChatService;
import eva.chat.service.UserSercice;
import eva.post.domain.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements Postservice {

    private UserSercice userSercice;

    private ChatService chatService;

    @Autowired
    public PostServiceImpl(UserSercice userSercice, ChatService chatService) {
        this.userSercice = userSercice;
        this.chatService = chatService;
    }

    @Override
    public List<Post> listAllPostsFromTo(String from, String to) {
        User userFrom = userSercice.getByNickname(from);
        chatService.resetNewPosts(from, to);
        return userFrom.getChats().get(to).getPostList();
    }

    @Override
    public void addPost(String from, String to, String pcontent) {
        Chat chatFrom = userSercice.getChatFromByNicknameTo(to, from);
        Chat chatTo = userSercice.getChatFromByNicknameTo(from, to);
        if (chatTo != null){
            chatService.savePosts(chatFrom, pcontent, "out");
            chatTo.addNewPosts();
            chatService.savePosts(chatTo, pcontent, "in");
        }else {
            chatService.savePosts(chatFrom, pcontent, "out");
            chatService.savePosts(chatFrom, "chat mit " + to + " ist gelöscht", "in");
        }
    }
}
