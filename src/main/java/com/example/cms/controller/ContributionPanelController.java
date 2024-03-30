package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.responsedto.ContributionPanelResponse;
import com.example.cms.service.ContributionPanelService;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ContributionPanelController {

	private ContributionPanelService contributionPanelService;
	
	@PutMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributor(@PathVariable Integer userId,@PathVariable Integer panelId){
		return contributionPanelService.addContributer(userId, panelId);
	}
	
	@DeleteMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> deleteContributor(@PathVariable Integer userId,@PathVariable Integer panelId){
		return contributionPanelService.deleteContainer(userId, panelId);
	}
}
