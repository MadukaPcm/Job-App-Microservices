package tz.maduka.uaa_v1.services.serviceImpl;
import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import tz.maduka.uaa_v1.dtos.payload.RegisterDto;
import tz.maduka.uaa_v1.dtos.response.UserResponse;
import tz.maduka.uaa_v1.models.User;
import tz.maduka.uaa_v1.repositories.UserRepository;

@Service
public class UserManager implements UserDetailsManager {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Override
//    public void createUser(UserDetails user) {
//
//        ((User) user).setPassword(passwordEncoder.encode(user.getPassword()));
//        // Save the user in the repository
//        userRepository.save((User) user);
//    }

    @Override
    public void createUser(UserDetails user) {
        if (!(user instanceof User)) {
            throw new IllegalArgumentException("UserDetails must be an instance of User.");
        }

        User newUser = (User) user;
        System.out.println("USER "+newUser);

        if (newUser.getUsername() == null || newUser.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }


    @Override
    public void updateUser(UserDetails user) {
        // You can implement this method to update user details if needed
        // For example, updating roles or other user information
    }

    @Override
    public void deleteUser(String username) {
        // You can implement this method to delete a user by username
        // Typically used when a user wants to delete their account
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // You can implement this method to change the user's password
        // For example, when a user wants to change their password
    }

//    @Override
//    public boolean userExists(String username) {
//
//        return false;
//    }

    @Override
    public boolean userExists(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the repository by username
        Optional<User> userOptional = userRepository.findByUsername(username);

        // Check if the user exists
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(MessageFormat.format("User with username {0} not found", username));
        }

        // Return the UserDetails extracted from the User entity
        return userOptional.get();
    }

}