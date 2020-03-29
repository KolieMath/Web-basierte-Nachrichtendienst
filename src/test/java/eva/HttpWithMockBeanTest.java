package eva;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import eva.post.domain.Post;
import eva.post.service.Postservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class HttpWithMockBeanTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private Postservice postservice;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
        when(postservice.listAllPostsFromTo("Elisa", "Marga")).thenReturn(List.of(new Post(), new Post()));
    }

    @Test
    public void getPosts() throws Exception{
        mockMvc.perform(get("/posts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("from", "Elisa")
                .param("to", "Marga"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPostsInMoreDetails() throws Exception {
        mockMvc.perform(get("/posts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("from", "Elisa")
                .param("to", "Marga"))
                .andExpect(status().isOk())
                .andExpect(view().name("posting"))
                .andExpect(model().attribute("listAllPosts", hasSize(2)))
                .andExpect(model().attribute("toUser", "Marga"))
                .andDo(print());
    }

    @Test
    public void newPost() throws Exception{
        mockMvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("from", "Elisa")
                .param("to", "Marga")
                .param("pcontent", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("posts?from=Elisa&to=Marga"));
    }




}
