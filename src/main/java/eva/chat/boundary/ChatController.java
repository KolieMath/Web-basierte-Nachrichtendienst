package eva.chat.boundary;

import eva.chat.domain.Chat;
import eva.chat.domain.User;
import eva.chat.service.UserSercice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    private UserSercice userSercice;

    @RequestMapping("/start")
    public String listingAllChats(@RequestParam String from, Model model){
        User userFrom = userSercice.getByNickname(from);
        List<Chat> targetList = new ArrayList<>(userFrom.getChats().values());
        model.addAttribute("fromUser", from);
        model.addAttribute("listAllChats", targetList);
        return "chat";
    }

    @RequestMapping(value = "/deleteChat", method = RequestMethod.POST)
    public String removeChat(@RequestParam String from, @RequestParam String to, Model model){
        model.addAttribute("fromUser", from);
        userSercice.deleteChatFromTo(from, to);
        return "redirect:start?from=" + from;
    }
}
