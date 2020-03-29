package eva.chat.service;

import eva.chat.domain.Chat;
import eva.chat.domain.User;
import eva.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceimpl implements UserSercice {

    private UserRepository userRepository;

    private ChatService chatService;

    @Autowired
    public UserServiceimpl(UserRepository userRepository, ChatService chatService) {
        this.userRepository = userRepository;
        this.chatService = chatService;
    }

    @Override
    public User getByNickname(String name) {
        return userRepository.findByNickname(name).orElse(null);
    }

    @Override
    public Object exitsNickname(String to) {
        return userRepository.findByNickname(to);
    }

    @Override
    public boolean userChatsContainsKeyfindByNickname(String from, String to) {
         Optional<User> userToOpt = userRepository.findByNickname(from);
        return userToOpt.map(user -> user.getChats().containsKey(to)).orElse(false);
    }

    @Override
    public void newChatFromTo(String from, String to) {
        User userFrom = userRepository.findByNickname(from).orElse(null);
        User userTo = userRepository.findByNickname(to).orElse(null);
        chatService.newChatBetween(userTo, userFrom);
    }

    @Override
    public void deleteChatFromTo(String from, String to) {
        User userFrom = userRepository.findByNickname(from).orElse(null);
        User userTo = userRepository.findByNickname(to).orElse(null);
        chatService.deleteChatBetween(userFrom, userTo);
    }

    @Override
    public Chat getChatFromByNicknameTo(String from, String to) {
        Optional<User> userToOpt = userRepository.findByNickname(to);
        return userToOpt.map(user -> user.getChats().get(from)).orElse(null);
    }
}
