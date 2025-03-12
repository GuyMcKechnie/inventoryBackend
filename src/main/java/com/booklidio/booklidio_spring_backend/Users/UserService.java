package com.booklidio.booklidio_spring_backend.Users;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(ObjectId userId) {
        return userRepository.findById(userId);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void editUser(ObjectId userId, User user) {
        Optional<User> existingUser = userRepository.findById(userId);
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
            throw new RuntimeException("User not found");
        }
    }
}
