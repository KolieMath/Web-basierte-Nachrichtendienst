package eva.post.boundary;

import eva.post.domain.Post;
import eva.post.service.Postservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    Postservice postservice;

    @RequestMapping("/posts")
    public String listAllPosts(@RequestParam String from, @RequestParam String to, Model model){
        List<Post> list = postservice.listAllPostsFromTo(from, to);
        model.addAttribute("listAllPosts", list);
        model.addAttribute("toUser", to);
        model.addAttribute("fromUser", from);
        model.addAttribute("currentUser", from);
        return "posting";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@RequestParam String from, @RequestParam String to, @RequestParam String pcontent, Model model){
        model.addAttribute("fromUser", from);
        model.addAttribute("currentUser", from);
        List<Post> list = postservice.listAllPostsFromTo(from, to);
        model.addAttribute("listAllPosts", list);
        postservice.addPost(from, to, pcontent);
        return "redirect:posts?from=" + from + "&to=" + to;
    }
}
