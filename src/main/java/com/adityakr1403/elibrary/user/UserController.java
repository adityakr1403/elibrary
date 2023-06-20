package com.adityakr1403.elibrary.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserRecord>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<UserRecord> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @GetMapping("/{email}")
    public UserRecord getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/delete/{email}")
    public void deleteUser(@PathVariable("email") String email) {
        userService.deleteUserByEmail(email);
    }

    @PutMapping("/update")
    public ResponseEntity<UserRecord> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }
}
