package com.equipment.manager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.equipment.manager.exception.ResourceNotFoundException;
import com.equipment.manager.model.Comment;
import com.equipment.manager.model.Equipment;
import com.equipment.manager.model.User;
import com.equipment.manager.payload.CommentRequest;
import com.equipment.manager.payload.CommentResponse;
import com.equipment.manager.repository.CommentRepository;
import com.equipment.manager.repository.EquipmentRepository;
import com.equipment.manager.repository.UserRepository;
import com.equipment.manager.security.CurrentUser;
import com.equipment.manager.security.UserPrincipal;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private EquipmentRepository equipmentRepository;
	
	@Autowired
	private UserRepository userRepository;
		
	public Comment createComment(CommentRequest commentRequest, Long equipmentId) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Equipment equipment = equipmentRepository
					.findById(equipmentId)
					.orElseThrow(() -> new ResourceNotFoundException("Equipment", "id", equipmentId));
		
		Comment comment = new Comment();
		
		User user = userRepository
					.findByUsername(authentication.getName())
					.orElseThrow(() -> new ResourceNotFoundException("User", "username", authentication.getName()));
		
		comment.setContent(commentRequest.getContent());
		comment.setUser(user);
		comment.setEquipment(equipment);
		
		return commentRepository.save(comment);
	}
	
	public List<CommentResponse> getCommentsByEquipmentId(Long equipmentId) {
		List<Comment> comments = commentRepository.findByEquipmentId(equipmentId);				
		List<CommentResponse> commentResponses = comments
				.stream()
				.map(comment -> {
					return new CommentResponse(
							comment.getId(),
							comment.getContent(),
							comment.getCreatedAt(),
							comment.getUpdatedAt(),
							comment.getUser().getUsername()
							);
				}).collect(Collectors.toList());
		
		return commentResponses;
	}
	
}
