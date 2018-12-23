package szczepaniak.ppss.AuthorizationService.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import szczepaniak.ppss.AuthorizationService.infrastructure.exeption.ResourceNotFoundException;
import szczepaniak.ppss.AuthorizationService.infrastructure.exeption.UserAlreadyExistsException;
import szczepaniak.ppss.AuthorizationService.model.User;
import szczepaniak.ppss.AuthorizationService.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public User create(String username, String password) {
        if (userRepository.existsByUsername(username))
            throw new UserAlreadyExistsException();

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setAdmin(false);
        return userRepository.save(user);
    }

    public User get(UUID id) {
        return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public User update(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public void delete(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User does not exist");
        }
        return user;
    }
}
