package com.adityakr1403.elibrary.user;

import com.adityakr1403.elibrary.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserRecord> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserRecord(
                        user.getUserId(),
                        user.getName(),
                        user.getEmail()
                        )).collect(Collectors.toList());
    }

    @Override
    public UserRecord addUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new UserRecord(user.getUserId(), user.getName(), user.getEmail());
    }

    @Override
    public UserRecord getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> new UserRecord(
                        user.getUserId(),
                        user.getName(),
                        user.getEmail()
                )).orElseThrow(() -> new UserNotFoundException("User with email " + email + " does not exist"));
    }

    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public UserRecord update(User user) {
        user.setRoles(user.getRoles());
        userRepository.save(user) ;
        return new UserRecord(user.getUserId(), user.getName(), user.getEmail());
    }
}
