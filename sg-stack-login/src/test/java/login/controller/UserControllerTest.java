package login.controller;

import login.repository.UserRepository;
import login.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Test
    public void testTest() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.get("/test").accept(MediaType.ALL);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("Test", result.getResponse().getContentAsString());
    }
    @Test
    public void getAllUsersTest() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.get("/getAll").accept(MediaType.ALL);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("application/json", result.getResponse().getContentType());
    }
}
