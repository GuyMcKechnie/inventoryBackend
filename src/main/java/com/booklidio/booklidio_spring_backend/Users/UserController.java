// package com.booklidio.booklidio_spring_backend.Users;

// import java.util.List;
// import java.util.Optional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//   private final UserService userService;

//   @Autowired
//   public UserController(UserService userService) {
//     this.userService = userService;
//   }

//   @PostMapping("/addUser")
//   public ResponseEntity<User> addUser(@RequestBody User user) {
//     User newUser = userService.addUser(user);
//     return ResponseEntity.ok(newUser);
//   }

//   @GetMapping("/getAllUsers")
//   public List<User> getAllUsers() {
//     return userService.getAllUsers();
//   }

//   @GetMapping("/getUser/{id}")
//   public ResponseEntity<User> getUserById(@RequestParam("id") Long id) {
//     Optional<User> user = userService.getUserById(id);
//     return user
//         .map(ResponseEntity::ok)
//         .orElseGet(() -> ResponseEntity.notFound().build());
//   }

//   @PutMapping("/updateUser")
//   public ResponseEntity<User> updateUser(
//       @RequestParam("id") Long id,
//       @RequestBody User user) {
//     User updatedUser = userService.updateUser(id, user);
//     return ResponseEntity.ok(updatedUser);
//   }

//   @DeleteMapping("/deleteUser")
//   public ResponseEntity<String> deleteUser(@RequestParam("id") Long id) {
//     userService.deleteUser(id);
//     return ResponseEntity.ok("User deleted successfully.");
//   }
// }
