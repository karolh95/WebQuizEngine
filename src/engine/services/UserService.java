package engine.services;

import engine.exceptions.EmailAlreadyExists;
import engine.models.User;
import engine.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(userNotFound(username));
    }

    public User createUser(User user) {

        if (userExist(user))
            throw new EmailAlreadyExists();

        user.setPassword(encoder.encode(user.getPassword()));

        System.out.println(user);
        return repository.save(user);
    }

    private Supplier<RuntimeException> userNotFound(String username) {
        return () -> new UsernameNotFoundException(username);
    }

    private boolean userExist(User user) {
        return repository.findByUsername(user.getUsername()).isPresent();
    }
}
