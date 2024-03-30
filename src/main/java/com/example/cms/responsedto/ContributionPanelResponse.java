package com.example.cms.responsedto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContributionPanelResponse {
	
	private int panelId;
	
}
