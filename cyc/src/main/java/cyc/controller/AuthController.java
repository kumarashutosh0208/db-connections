package cyc.controller;

import cyc.dataModel.User;
import cyc.dtoModel.AuthRequest;
import cyc.security.JwtUtil;
import cyc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthRequest authRequest) {
        if (authRequest == null || authRequest.getEmail() == null || authRequest.getPassword() == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Email and password are required");
            return ResponseEntity.badRequest().body(response);
        }

        User user = userService.findByEmail(authRequest.getEmail());
        if (user == null || !passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        String token = jwtUtil.generateToken(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("token", token);
        response.put("user", user);
        return ResponseEntity.ok(response);
    }
}
