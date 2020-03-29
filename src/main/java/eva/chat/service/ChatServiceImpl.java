package eva.chat.service;

import eva.chat.domain.Chat;
import eva.chat.domain.User;
import eva.chat.repository.ChatRepository;
import eva.post.domain.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public void newChatBetween(User userTo, User userFrom) {
        Chat chat = new Chat(userTo.getNickname(), userFrom);
        Chat chat2 = new Chat(userFrom.getNickname(), userTo);
        if (userFrom.addChat(chat))
            chatRepository.save(chat);
        if (userTo.addChat(chat2))
            chatRepository.save(chat2);
    }

    @Override
    public void deleteChatBetween(User userFrom, User userTo) {
        String from = userFrom.getNickname();
        String to = userTo.getNickname();
        Chat chat = userFrom.getChats().get(to);
        userFrom.getChats().remove(to);
        chatRepository.delete(chat);
    }

    @Override
    public void savePosts(Chat chat, String pcontent, String inOrOut) {
        DateTimeFormatter germanFormatter = DateTimeFormatter
                .ofLocalizedTime(FormatStyle.MEDIUM)
                .withLocale(Locale.GERMAN);
        String time = LocalTime.now().format(germanFormatter);

        Post post = new Post(pcontent, time, chat, inOrOut);
        chat.addPost(post);
        chatRepository.save(chat);

    }

    @Override
    public void resetNewPosts(String from, String to) {
        chatRepository.findByUser_NicknameAndChatWith(from, to)
                .ifPresent(chat -> {
                    chat.resetNewPosts();
                    chatRepository.save(chat);
                });
    }
}
