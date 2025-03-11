package cyc.controller;


import cyc.dataModel.User;
import cyc.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("user")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User added");
    }

    @GetMapping("get/user/{userId}")
    public User getUserByUserId(@PathVariable String userId) {

        return  userService.getUserByUserId(userId);
    }

    @PostMapping("login")
    public ResponseEntity<String> addUser(@RequestBody U) {
        userService.addUser(user);
        return ResponseEntity.ok("User added");
    }

}
