package com.devsoftbd.personrestapi.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsoftbd.personrestapi.model.HobbyModel;
import com.devsoftbd.personrestapi.service.HobbyService;

@Service
public class HobbyServiceImpl implements HobbyService {

	/**
	 * This method will take a string array of hobby and will return a
	 * hobbyModelList If hobby array null/empty then return null
	 */
	@Override
	public List<HobbyModel> getHobbyListFromHobbyStringArray(String[] hobby) {
		if (hobby != null && hobby.length > 0) {
			return Arrays.stream(hobby).map(hby -> new HobbyModel(hby)).collect(Collectors.toList());
		}
		return null;
	}

}
