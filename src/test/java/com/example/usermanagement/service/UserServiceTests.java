import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void whenRegisterUser_thenSaveUser() {
        User user = new User("JohnDoe", "john@example.com", "password");

        userService.register(user);

        verify(userRepository).save(user);
    }

    @Test
    public void whenFindUserByUsername_thenUserShouldBeFound() {
        User user = new User("JohnDoe", "john@example.com", "password");
        when(userRepository.findByUsername("JohnDoe")).thenReturn(user);

        User found = userService.findUserByUsername("JohnDoe");

        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }
}