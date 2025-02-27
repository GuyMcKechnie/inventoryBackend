package com.booklidio.booklidio_spring_backend.Users;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User addUser(User user) {
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  @Transactional
  public User updateUser(Long id, User updatedUser) {
    Optional<User> existingUser = userRepository.findById(id);
    if (existingUser.isPresent()) {
      User user = existingUser.get();
      user.setFIRSTNAME(updatedUser.getFIRSTNAME());
      user.setLASTNAME(updatedUser.getLASTNAME());
      user.setEMAIL(updatedUser.getEMAIL());
      user.setCELLPHONE(updatedUser.getCELLPHONE());
      user.setMARKETING(updatedUser.getMARKETING());
      return userRepository.save(user);
    } else {
      throw new RuntimeException("User not found.");
    }
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
