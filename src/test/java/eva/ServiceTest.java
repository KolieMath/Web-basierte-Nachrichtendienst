package eva;

import static org.assertj.core.api.Assertions.assertThat;

import eva.chat.domain.Chat;
import eva.chat.repository.ChatRepository;
import eva.chat.repository.UserRepository;
import eva.chat.service.ChatService;
import eva.chat.service.UserSercice;
import eva.post.repository.PostRepository;
import eva.post.service.Postservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

@SpringBootTest//(classes = WebChatAppApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ChatService chatService;

    @Autowired
    Postservice postservice;

    @Autowired
    UserSercice userSercice;

    String elisa = "Elisa", marga = "Marga", unknown = "Unknown", emptyuser = "";

    @BeforeEach
    public void setupDatabase(){
        System.out.println("Tests are starting");
    }

    @Test
    @Transactional
    public void testExistsChatFromTo(){
        Chat chat = userSercice.getChatFromByNicknameTo(elisa, marga);
        assertThat(chat.getUser().getNickname()).isEqualTo(marga);
    }

    @Test
    @Transactional
    public void testNewChatFromTo(){
        Chat chat = userSercice.getChatFromByNicknameTo(marga, elisa);
        assertThat(chat.getUser().getNickname()).isEqualTo(elisa);
    }
}
