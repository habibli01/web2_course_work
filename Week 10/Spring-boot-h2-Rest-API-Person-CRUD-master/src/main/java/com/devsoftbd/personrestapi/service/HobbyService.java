package com.devsoftbd.personrestapi.service;

import java.util.List;

import com.devsoftbd.personrestapi.model.HobbyModel;

public interface HobbyService {
	
	public List<HobbyModel> getHobbyListFromHobbyStringArray(String[] hobby);
}
