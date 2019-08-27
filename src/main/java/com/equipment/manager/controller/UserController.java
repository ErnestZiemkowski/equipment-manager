package com.equipment.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.equipment.manager.exception.ResourceNotFoundException;
import com.equipment.manager.model.User;
import com.equipment.manager.payload.UserIdentityAvailability;
import com.equipment.manager.payload.UserProfile;
import com.equipment.manager.payload.UserSummary;
import com.equipment.manager.repository.UserRepository;
import com.equipment.manager.security.CurrentUser;
import com.equipment.manager.security.UserPrincipal;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername());
		return userSummary;
	}
	
	@GetMapping("/user/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}
	
	@GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }
   
   	@GetMapping("/users/{username}")
   	public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
   		User user = userRepository.findByUsername(username)
   				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
   		
   		UserProfile userProfile = new UserProfile(user.getId(), user.getUsername());
   		return userProfile;
   	}
}
