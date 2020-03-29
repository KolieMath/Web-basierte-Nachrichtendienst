package eva.config;

import eva.chat.domain.Chat;
import eva.chat.domain.User;
import eva.chat.repository.ChatRepository;
import eva.chat.repository.UserRepository;
import eva.post.domain.Post;
import eva.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Slf4j
@Component
public class InitializeDatabase {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    PostRepository postRepository;

    @PostConstruct
    public void init(){

        log.debug("Database initialized");

        User user = new User();
        user.setNickname("Elisa");
        User user2 = new User();
        user2.setNickname("Marga");
        userRepository.save(user);
        userRepository.save(user2);
        User user3 = new User();
        user3.setNickname("Frieda");
        userRepository.save(user3);

        Chat chat = new Chat(user2.getNickname(), user);
        Chat chat2 = new Chat(user.getNickname(), user2);
        user.addChat(chat);
        user2.addChat(chat2);
        chatRepository.save(chat);
        chatRepository.save(chat2);

        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                .withLocale(Locale.GERMAN);
        LocalTime localTime = LocalTime.of(17, 56, 0, 0);

        String time = localTime.minusMinutes(10).plusSeconds(4).format(germanFormatter);

        Post post1 = new Post("Hallo Marga", time, chat, "out");
        postRepository.save(post1);
        Post post2 = new Post("Hallo Marga", time, chat2, "in");
        postRepository.save(post2);
        chat.addPost(post1);
        chat2.addPost(post2);

        time = localTime.minusMinutes(8).plusSeconds(42).format(germanFormatter);

        Post post3 = new Post("Hi Elisa", time, chat, "in");
        postRepository.save(post3);
        Post post4 = new Post("Hi Elisa", time, chat, "out");
        postRepository.save(post4);
        chat.addPost(post3);
        chat2.addPost(post4);

        time = localTime.minusMinutes(6).plusSeconds(33).format(germanFormatter);
        post3 = new Post("Hab heute spring gelernt", time, chat, "in");
        postRepository.save(post3);
        post4 = new Post("hab heute spring gelernt", time, chat2, "out");
        postRepository.save(post4);
        chat.addPost(post3);
        chat2.addPost(post4);

        time = localTime.minusMinutes(5).plusSeconds(5).format(germanFormatter);
        post3 =  new Post("Wie hoch bist du gekommen", time, chat, "out");
        postRepository.save(post3);
        post4 = new Post("Wie hoch bist du gekommen", time, chat2, "in");
        postRepository.save(post4);
        chat.addPost(post3);
        chat.addPost(post4);

        time = localTime.minusMinutes(3).plusSeconds(17).format(germanFormatter);
        post3 = new Post("mit 5 bohnen ging es ganz gut", time, chat, "in");
        postRepository.save(post3);
        post4 = new Post("mit 5 bohnen ging es ganz gut", time, chat2, "out");
        postRepository.save(post4);
        chat.addPost(post3);
        chat2.addPost(post4);

        time = localTime.plusMinutes(1).plusSeconds(38).format(germanFormatter);
        post3 = new Post("Ich musste 12 nehmen puhh", time, chat, "out");
        postRepository.save(post3);
        post4 = new Post("Ich musste 12 nehmen puhh", time, chat2, "in");
        postRepository.save(post4);
        chat.addPost(post3);
        chat2.addPost(post4);

        time = localTime.plusMinutes(4).plusSeconds(2).format(germanFormatter);
        post3 = new Post("Du musst auch immer Übertreiben", time, chat, "in");
        postRepository.save(post3);
        post4 = new Post("Du musst auch immer Übertreiben", time, chat2, "out");
        postRepository.save(post4);
        chat.addPost(post3);
        chat2.addPost(post4);

        chatRepository.save(chat);
        chatRepository.save(chat2);

    }
}
