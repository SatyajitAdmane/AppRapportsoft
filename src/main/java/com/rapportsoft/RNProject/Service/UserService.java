package com.rapportsoft.RNProject.Service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	public boolean authenticate(String email, String password) {
        // Replace this with actual database check
		System.out.println(email);
		System.out.println(password);
        return "satyajitadmane7@gmail.com".equals(email) && "password123".equals(password);
    }

}
