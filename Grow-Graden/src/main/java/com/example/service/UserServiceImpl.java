package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.config.JwtProvider;
import com.example.exception.UserException;
import com.example.model.Address;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.request.LoginRequest;
import com.example.responce.AuthResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;




@Service

public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	private CustomeUserServiceImpl customeUserServiceImpl;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder,
			CustomeUserServiceImpl customeUserServiceImpl) {
		super();
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
		this.passwordEncoder = passwordEncoder;
		this.customeUserServiceImpl = customeUserServiceImpl;
	}

	@Override
	public AuthResponse createUser(User user) throws UserException {
		// TODO Auto-generated method stub
		
		
		Optional<User> optional = userRepository.findByEmail(user.getEmail());

		if (optional.isPresent())
			throw new UserException("Email is already used try with another email");

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepository.save(user);

		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.genrateTokan(authentication);
		
	    AuthResponse authResponse = new AuthResponse();
		 
		 authResponse.getUser().put("firstName",user.getFirstName());
		 authResponse.getUser().put("lastName", user.getLastName());
		 authResponse.getUser().put("email", user.getEmail());
		 authResponse.getUser().put("mobileNumber", user.getMobileNumber());
		 
		 
		 authResponse.setJwt(token);
		 authResponse.setMessage("Sign Up Success");


		 return authResponse;
	}

	@Override
	public AuthResponse userLogin(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		

		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.genrateTokan(authentication);
		
		 Optional<User> optional =  userRepository.findByEmail(loginRequest.getEmail());
		 
		 
		 
		 User showUser =  optional.orElseThrow(()-> new UserException("User Not Resister"));
		 AuthResponse authResponse = new AuthResponse();
		 
		 authResponse.getUser().put("firstName",showUser.getFirstName());
		 authResponse.getUser().put("lastName", showUser.getLastName());
		 authResponse.getUser().put("email", showUser.getEmail());
		 authResponse.getUser().put("mobileNumber", showUser.getMobileNumber());
		 authResponse.getUser().put("role", showUser.getRole());
		 
		 authResponse.setJwt(token);
		 authResponse.setMessage("Sign In Success");
		 
		 
		 System.out.println(showUser.getFirstName());

		return authResponse;
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		// TODO Auto-generated method stub
		try {
			// Parse the JWT to extract user information
			Claims claims = Jwts.parser().setSigningKey("yourSecretKey") // Replace with your actual secret key
					.parseClaimsJws(jwt).getBody();

			// Extract user information from claims
			String username = claims.getSubject();
			// You can also extract other user-related data if present in the JWT claims

			// Query the database or your user repository to fetch the user profile based on
			// the extracted information
			Optional<User> optional = userRepository.findByEmail(username);

			User userProfile = optional.orElseThrow(() -> new UserException("User profile not found."));

			return userProfile;
		} catch (JwtException | IllegalArgumentException e) {
			throw new UserException("Invalid JWT or user profile not found." + e);
		}
	}

	private Authentication authenticate(String username, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails = customeUserServiceImpl.loadUserByUsername(username);
		if (userDetails == null)
			throw new BadCredentialsException("Invalid Username");
		if (!passwordEncoder.matches(password, userDetails.getPassword()))

			throw new BadCredentialsException("Invalid Password");
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

	// user adding multiple address to his account;
	@Override
	public String addAddressToUserAccount(Integer userId, Address address) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("User Not found for the given userid : " + userId));
		user.getAddresses().add(address);
		userRepository.save(user);
		return "User Address updated successfully.";
	}

}
