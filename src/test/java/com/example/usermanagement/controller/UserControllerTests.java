import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void whenPostRegister_thenCreateUser() throws Exception {
        mockMvc.perform(post("/api/public/register")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content("{\"username\":\"JohnDoe\",\"email\":\"john@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetUser_thenReturnUser() throws Exception {
        String username = "JohnDoe";
        User mockUser = new User(username, "john@example.com", "password");
        when(userService.findUserByUsername(username)).thenReturn(mockUser);

        mockMvc.perform(get("/api/user/me")
                .accept(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"username\":\"JohnDoe\",\"email\":\"john@example.com\"}"));
    }
}