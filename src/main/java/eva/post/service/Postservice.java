package eva.post.service;

import eva.post.domain.Post;

import java.util.List;

public interface Postservice {

    List<Post> listAllPostsFromTo(String from, String to);

    void addPost(String from, String to, String pcontent);
}
