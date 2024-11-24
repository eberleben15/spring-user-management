@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        // Add user registration logic
        return userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}