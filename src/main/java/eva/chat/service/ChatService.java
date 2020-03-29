package eva.chat.service;

import eva.chat.domain.Chat;
import eva.chat.domain.User;

public interface ChatService {

    void newChatBetween(User userTo, User userFrom);

    void deleteChatBetween(User userFrom, User userTo);

    void savePosts(Chat chat, String pcontent, String inOrOut);

    void resetNewPosts(String from, String to);
}
