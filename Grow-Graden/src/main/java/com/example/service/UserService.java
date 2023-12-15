package com.example.service;

import com.example.exception.UserException;
import com.example.model.Address;
import com.example.model.User;
import com.example.request.LoginRequest;
import com.example.responce.AuthResponse;

public interface UserService {
	
	/**
	 * This method for create new user 
	 * @param user
	 * @return response of AuthResponse class 
	 * @throws UserException
	 */
	public AuthResponse createUser(User user) throws UserException;
	
	/**
	 * This method is for user for getting logged in to the application.
	 * @param LoginRequest
	 * @return AuthResponse 
	 */
	public AuthResponse userLogin(LoginRequest loginRequest);
	
	
	// /**
	//  * This method for find user using user id 
	//  * @param userId
	//  * @return User 
	//  */
	// public User findUseById(Long userId) throws UserException;
	
	/**
	 * This method find user profile using jwt 
	 * @param jwt
	 * @return User 
	 * @throws UserException
	 */
	public User findUserProfileByJwt(String jwt) throws UserException;
	
	/**
	 * This method allows user to add the multiple address to his account.
	 * @param address
	 * @return String
	 */
	String addAddressToUserAccount(Integer userId,Address address);
}
