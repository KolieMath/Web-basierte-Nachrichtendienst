package eva.chat.service;

import eva.chat.domain.Chat;
import eva.chat.domain.User;

public interface UserSercice {

    User getByNickname(String name);

    Object exitsNickname(String to);

    boolean userChatsContainsKeyfindByNickname(String from, String to);

    void newChatFromTo(String from, String to);

    void deleteChatFromTo(String from, String to);

    Chat getChatFromByNicknameTo(String from, String to);
}
