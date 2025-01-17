package com.rapportsoft.RNProject.security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.rapportsoft.RNProject.entities.AppUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHelper {
	public static final long JWT_TOKEN_VALIDITY = 11 * 60 * 60;
    private String secret = "afafasfafafasfsfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
	 public String generateToken(AppUser userDetails) {
	        return doGenerateToken(userDetails.getUser_Id());
	        
	        }
	 // Check if the token has expired
	    private Boolean isTokenExpired(String token) {
	        final Date expiration = getExpirationDateFromToken(token);
	        return expiration.before(new Date());
	    }
	 // Retrieve expiration date from jwt token
	    public Date getExpirationDateFromToken(String token) {
	        return getClaimFromToken(token, Claims::getExpiration);
	    }
	 private String doGenerateToken(String username) {
	        return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
	                .signWith(SignatureAlgorithm.HS512, secret).compact();
	    }
	 
	 public Boolean validateToken(String token, String username) {
	        final String tokenUsername = getUsernameFromToken(token);
	        return (username.equals(tokenUsername) && !isTokenExpired(token));
	    }

}
