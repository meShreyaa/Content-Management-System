package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.entity.User;
import com.example.cms.exception.PanelNotFoundById;
import com.example.cms.exception.UserNotFoundById;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributorPanelRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.responsedto.ContributionPanelResponse;
import com.example.cms.service.ContributionPanelService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContributionPanelServiceImpl implements ContributionPanelService{

	private ContributorPanelRepository contributorPanelRepository;
	private UserRepository userRepository;
	private BlogRepository blogRepository;
	private ResponseStructure<ContributionPanelResponse> responseStructure;
	
	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributer(Integer userId, Integer panelId){
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		return userRepository.findByUserEmail(email).map(owner -> {
			return contributorPanelRepository.findById(panelId).map(panel ->{
				if(!blogRepository.existsByUserAndContributionPanel(owner,panel))
					 new IllegalAccessException("Failed to add Contributor");
				return userRepository.findById(userId).map(contributor -> {
					if(! panel.getUsers().contains(contributor)) {
						panel.getUsers().add(contributor);
						contributorPanelRepository.save(panel);
					}
					return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
							.setMessage("Added to Panel Successfully")
							.setData(mapToContributionResponse(panel)));
				}).orElseThrow(()-> new UserNotFoundById("Unable to add the user"));
			}).orElseThrow(()-> new PanelNotFoundById("Unable to add the user"));
		}).get();
	}


	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> deleteContainer(Integer userId, Integer panelId) {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		
		return userRepository.findByUserEmail(email).map(owner -> {
			return contributorPanelRepository.findById(panelId).map(panel ->{
				if(!blogRepository.existsByUserAndContributionPanel(owner, panel))
					new IllegalAccessException("Failed to delete Contributor");
				return userRepository.findById(userId).map(contributor -> {
					panel.getUsers().remove(contributor);
					contributorPanelRepository.save(panel);
					return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
							.setMessage("Deleted From Panel Successfully")
							.setData(mapToContributionResponse(panel)));
				}).orElseThrow(()-> new UserNotFoundById("Unable to delete the user"));
			}).orElseThrow(()-> new PanelNotFoundById("Unable to delete the user"));
		}).get();
		
	}
	
	private ContributionPanelResponse mapToContributionResponse(ContributionPanel panel) {
		return ContributionPanelResponse.builder().panelId(panel.getPanelId()).build();
	}


}
