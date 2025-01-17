package com.rapportsoft.RNProject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rapportsoft.RNProject.Service.UserServiceImpl;
import com.rapportsoft.RNProject.entities.AppUser;
import com.rapportsoft.RNProject.entities.JwtRequest;
import com.rapportsoft.RNProject.entities.JwtResponse;
import com.rapportsoft.RNProject.security.JwtHelper;

@CrossOrigin("*")
@RequestMapping("/auth")
@RestController
public class AuthController {
	
	@Autowired
	private BCryptPasswordEncoder passwordencode;
	
	@Autowired
	private UserServiceImpl uservice;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/login/{otp}")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest,
			@RequestHeader("React-Page-Name") String reactPageName, @PathVariable("otp") String otp) throws Exception {
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
			AppUser user = (AppUser) this.uservice.loadUserByUsername(jwtRequest.getUsername());
			
			if (user.getStop_Trans() == 'Y') {
				throw new Exception("User is not active");
			}

			if (user.getStatus() == "D") {
				throw new Exception("User not exist");
			}

			if (!user.getStatus().equals("A")) {
				throw new Exception("User not exist");
			}

			if (!user.getBranch_Id().equals(jwtRequest.getBranchid())) {
				throw new Exception("Invalid branch for the user");
			}

			String userType = user.getUser_Type();
			
			if ("Party".equals(userType) || "CHA".equals(userType) || "Carting Agent".equals(userType)) {
				System.out.println("User type iS Not User ");
				return (ResponseEntity<?>) ResponseEntity.ok();
				
				}
			
			
			else {
				if (user.getDefaultotp().equals(otp) || user.getOTP().equals(otp)) {
					
					String userId = user.getUser_Name();
					String companyId = user.getCompany_Id();
					String branchId = user.getBranch_Id();
					String role = user.getRole();
					
					String companyname = "Rapportsoft Consulting & Technology Pvt Ltd";
					String branchname = "b1";
					
					AppUser userDetails = (AppUser) this.uservice.loadUserByUsername(jwtRequest.getUsername());
					
					String token = this.jwtHelper.generateToken(userDetails);
					
					JwtResponse jwtResponse = new JwtResponse(token, userDetails.getUsername(), userId, branchId,
							companyId, role, companyname, branchname, userDetails.getLogintype(),
							userDetails.getLogintypeid(), user.getUser_Type()
							);
					return ResponseEntity.ok(jwtResponse);
					
				}
				else {
					return ResponseEntity.status(400).body("Please enter correct otp..");
				}
			}

		}
		catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User Not Found");
		}
		
		
	}
	
	
	private void authenticate(String username, String password) throws Exception {

		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {

			throw new Exception("User Disable" + e.getMessage());

		} catch (BadCredentialsException e) {

			throw new Exception("Invalide credential" + e.getMessage());
		}

	}

}
