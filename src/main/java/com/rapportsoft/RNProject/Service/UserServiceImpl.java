package com.rapportsoft.RNProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.rapportsoft.RNProject.entities.AppUser;
import com.rapportsoft.RNProject.repository.UserRepository;

@Component
public class UserServiceImpl  implements UserDetailsService{
	@Autowired
	public UserRepository urepo;
	
//	@Autowired
//	public BranchRepo brepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    AppUser user = urepo.findByUser_Id(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with the provided username");
        }
        return  user;
	}

}
