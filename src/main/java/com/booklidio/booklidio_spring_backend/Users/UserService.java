package com.booklidio.booklidio_spring_backend.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    public void addUser(User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            System.out.println("User with email: " + user.getEmail() + " already exists");
            throw new Exception("User with email: " + user.getEmail() + " already exists");
        }
        try {
            user.setUserId(UUID.randomUUID().toString());
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void editUser(String userId, User user) throws Exception {
        Optional<User> existingUser = userRepository.findByUserId(userId);
        if (existingUser.isPresent()) {
            existingUser.get().setFirstName(user.getFirstName());
            existingUser.get().setLastName(user.getLastName());
            existingUser.get().setEmail(user.getEmail());
            existingUser.get().setCellphone(user.getCellphone());
            existingUser.get().setAllowsMarketing(user.getAllowsMarketing());
            existingUser.get().setIsBuyer(user.getIsBuyer());
            existingUser.get().setIsSeller(user.getIsSeller());
            userRepository.save(existingUser.get());
        } else {
            throw new Exception("Edit: User not found");
        }
    }

    public void duplicateUser(String userId) throws Exception {
        Optional<User> existingUser = userRepository.findByUserId(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            User duplicatedUser = new User();
            duplicatedUser.setUserId(UUID.randomUUID().toString());
            duplicatedUser.setFirstName(user.getFirstName());
            duplicatedUser.setLastName(user.getLastName());
            duplicatedUser.setEmail(user.getEmail().concat(UUID.randomUUID().toString().split("-")[0]));
            duplicatedUser.setCellphone(user.getCellphone());
            duplicatedUser.setAllowsMarketing(user.getAllowsMarketing());
            duplicatedUser.setIsBuyer(user.getIsBuyer());
            duplicatedUser.setIsSeller(user.getIsSeller());
            userRepository.save(duplicatedUser);
        } else {
            throw new Exception("Duplicate: User not found");
        }
    }

    public void deleteUser(String userId) throws Exception {
        Optional<User> existingUser = userRepository.findByUserId(userId);
        if (existingUser.isPresent()) {
            userRepository.delete(existingUser.get());
        } else {
            throw new Exception("Delete: User not found");
        }
    }
}
