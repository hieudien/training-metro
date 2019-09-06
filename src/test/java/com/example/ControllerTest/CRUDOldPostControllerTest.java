package com.example.ControllerTest;

import com.example.Controllers.CRUDOldPostController;
import com.example.Controllers.CRUDOldPostController;
import com.example.Entity.OldPost;
import com.example.Entity.OldPost;
import com.example.Service.CRUDOldPostService;
import com.example.Service.CRUDOldPostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Test for {@link com.example.Controllers.CRUDOldPostController}.
 */
@RunWith(MockitoJUnitRunner.class)
public class CRUDOldPostControllerTest {
    @Mock
    CRUDOldPostService crudOldPostService;
    @InjectMocks
    CRUDOldPostController sut;
    private MockMvc mockMvc;

    private static final String OLD_POST_PATH = "/oldPost";
    private static final String SAVE = "/save";
    private static final String FIND = "/find/{id}";
    private static final String UPDATE = "/update";
    private static final String DELETE = "/delete/{id}";

    /**
     * Setup
     */
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    /**
     * Test save old post success
     * Input:
     * Old post that not exists in DB
     * Output:
     * Response with OK status
     */
    @Test
    public void saveOldPostTest1() throws Exception {
        // setup
        OldPost oldPost = new OldPost();
        oldPost.setOldPostId("1234");
        oldPost.setOldPostCode("code");
        Mockito.when(crudOldPostService.saveOldPost(oldPost)).thenReturn(oldPost);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(oldPost);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .post(OLD_POST_PATH + SAVE).contentType("application/json")
                .content(content))
                // verify
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Test save a OldPost fail,
     * Input:
     * Request OldPost is null
     * Output:
     * Response with Bad request Status
     */
    @Test
    public void saveOldPostTest2() throws Exception {
        // setup
        OldPost OldPost = null;
        Mockito.when(crudOldPostService.saveOldPost(OldPost)).thenReturn(OldPost);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(OldPost);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .post(OLD_POST_PATH + SAVE).contentType("application/json")
                .content(content))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test save a OldPost, duplicate key error
     * Input:
     * A OldPost that exists in DB
     * Output:
     * Response with Server Error Status
     */
    @Test
    public void saveOldPostTest3() throws Exception {
        // setup
        OldPost oldPost = new OldPost();
        oldPost.setOldPostId("1234");
        oldPost.setOldPostCode("code");
        Mockito.when(crudOldPostService.saveOldPost(oldPost)).thenReturn(null);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(oldPost);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .post(OLD_POST_PATH + SAVE).contentType("application/json")
                .content(content))
                // verify
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    /**
     * Test find OldPost by oldPostId success
     * Input:
     * oldPostId that exists in DB
     * Output:
     * Response with OK status
     *
     * @throws Exception exception
     */
    @Test
    public void findOldPostTest1() throws Exception {
        // setup
        String oldPostId = "1234";
        Mockito.when(crudOldPostService.find(oldPostId)).thenReturn(new OldPost());
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(OLD_POST_PATH + FIND, oldPostId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Test find OldPost by oldPostId success, input oldPostId is null
     * Input:
     * null
     * Output:
     * Response with Bad Request Status
     *
     * @throws Exception exception
     */
    @Test
    public void findOldPostTest2() throws Exception {
        // setup
        String oldPostId = null;
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(OLD_POST_PATH + FIND, oldPostId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test find oldPostId by oldPostId success, input oldPostId is not a number
     * Input:
     * input oldPostId is not a number
     * Output:
     * Response with Bad Request Status
     *
     * @throws Exception exception
     */
    @Test
    public void findOldPostTest3() throws Exception {
        // setup
        String oldPostId = "oldPostId";
        Mockito.when(crudOldPostService.find(oldPostId)).thenReturn(new OldPost());
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(OLD_POST_PATH + FIND, oldPostId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test find OldPost by oldPostId, there is no record be found
     * Input:
     * oldPostId that exists in DB
     * Output:
     * Response with Not Found Status
     *
     * @throws Exception exception
     */
    @Test
    public void findOldPostTest4() throws Exception {
        // setup
        String oldPostId = "1234";
        Mockito.when(crudOldPostService.find(oldPostId)).thenReturn(null);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(OLD_POST_PATH + FIND, oldPostId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Test update a OldPost success
     * Input:
     * A OldPost that exists in DB with some different information
     * Output:
     * Response with updated OldPost and OK status
     *
     * @throws Exception exception
     */
    @Test
    public void updateOldPostTest1() throws Exception {
        // setup
        OldPost oldPost = new OldPost();
        oldPost.setOldPostId("1234");
        oldPost.setOldPostCode("code");
        Mockito.when(crudOldPostService.updateOldPost(oldPost)).thenReturn(oldPost);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(oldPost);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .post(OLD_POST_PATH + UPDATE).contentType("application/json")
                .content(content))
                // verify
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Test update a OldPost fail, input OldPost is null
     * Input:
     * null
     * Output:
     * Response with Bad Request Status
     *
     * @throws Exception exception
     */
    @Test
    public void updateOldPostTest2() throws Exception {
        // setup
        OldPost oldPost = null;
        Mockito.when(crudOldPostService.updateOldPost(oldPost)).thenReturn(oldPost);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(oldPost);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .post(OLD_POST_PATH + UPDATE).contentType("application/json")
                .content(content))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test update a OldPost fail, server error
     * Input:
     * A OldPost that exists in DB with some different information
     * Output:
     * Response with Server Error Status
     *
     * @throws Exception exception
     */
    @Test
    public void updateOldPostTest3() throws Exception {
        // setup
        OldPost oldPost = new OldPost();
        oldPost.setOldPostId("1234");
        oldPost.setOldPostCode("code");
        Mockito.when(crudOldPostService.updateOldPost(oldPost)).thenReturn(null);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(oldPost);
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .post(OLD_POST_PATH + UPDATE).contentType("application/json")
                .content(content))
                // verify
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    /**
     * Test delete a OldPost success
     * Input:
     * oldPostId that exists in DB
     * Output:
     * Response with OK status
     */
    @Test
    public void deleteOldPostTest1() throws Exception {
        // setup
        String oldPostId = "1234";
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(OLD_POST_PATH + DELETE, oldPostId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Test delete a OldPost fail, input oldPostId is null
     * Input:
     * null
     * Output:
     * Response with Bad Request Status
     */
    @Test
    public void deleteOldPostTest2() throws Exception {
        // setup
        String oldPostId = null;
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(OLD_POST_PATH + DELETE, oldPostId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test delete a OldPost fail, input oldPostId is not a number
     * Input:
     * input oldPostId is not a number
     * Output:
     * Response with Bad Request Status
     */
    @Test
    public void deleteOldPostTest3() throws Exception {
        // setup
        String oldPostId = "oldPostId";
        // exercise
        mockMvc.perform(MockMvcRequestBuilders
                .get(OLD_POST_PATH + DELETE, oldPostId))
                // verify
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
