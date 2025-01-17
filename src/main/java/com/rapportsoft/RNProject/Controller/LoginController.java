package com.rapportsoft.RNProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rapportsoft.RNProject.Dtos.UserLoginRequest;
import com.rapportsoft.RNProject.Service.UserService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://192.168.1.142:8081")
public class LoginController {
	 @Autowired
	    private UserService userService;

	    @PostMapping("/loginUser")
	    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
	        boolean isAuthenticated = userService.authenticate(request.getEmail(), request.getPassword());
	        
	        System.out.println("isAuthenticated "+isAuthenticated);

	        if (isAuthenticated) {
	            return ResponseEntity.ok("Login successful");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        }
	    }

}
