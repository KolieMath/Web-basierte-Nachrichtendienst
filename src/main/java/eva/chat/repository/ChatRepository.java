package eva.chat.repository;

import eva.chat.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Integer>{

    Optional<Chat> findByUser_NicknameAndChatWith(String from, String to);

}
