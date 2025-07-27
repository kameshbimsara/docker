package lk.acpt.demo_afsd_two.controller;

import lk.acpt.demo_afsd_two.dto.AuthDto;
import lk.acpt.demo_afsd_two.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthDto authDto) {
        boolean success = authService.UserRegister(authDto.getEmail(), authDto.getPassword());
        if (success) {
            return ResponseEntity.ok("User Registered Successfully");
        } else {
            return ResponseEntity.badRequest().body("User Already Exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDto authDto) {
        String token = authService.UserLogin(authDto.getEmail(), authDto.getPassword());
        if (token != null) {
            return ResponseEntity.ok("JWT Token: " + token);
        } else {
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
    }
}
