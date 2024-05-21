package To.DoApp.services;

import java.util.Collections;
import java.util.List;

import To.DoApp.dto.StatusCountDTO;
import To.DoApp.repositories.TodoItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DashboardService {
	@Autowired
	private TodoItemsRepository todoRepository;

	public List<StatusCountDTO> getTodoCountGroupByStatus() {
		List<StatusCountDTO> list = todoRepository.findCountGroupByStatus();
		if (!StringUtils.isEmpty(list))
			Collections.sort(list, StatusCountDTO.compareByStatus);
		return list;
	}
}
