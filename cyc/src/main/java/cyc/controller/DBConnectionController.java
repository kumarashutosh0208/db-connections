package cyc.controller;

import cyc.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/db")
@Slf4j
public class DBConnectionController {

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        boolean connected = userService.testDatabaseConnection();
        Map<String, Object> response = new HashMap<>();
        response.put("status", connected ? "UP" : "DOWN");
        response.put("database", connected ? "MongoDB connection successful" : "MongoDB connection failed");
        response.put("connected", connected);
        return connected ? ResponseEntity.ok(response) : ResponseEntity.status(503).body(response);
    }

    @GetMapping
    public String getUsers() {
        return "Application is running";
    }
}
