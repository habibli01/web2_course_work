package To.DoApp.repositories;

import java.util.List;
import java.util.Optional;

import To.DoApp.dto.StatusCountDTO;
import To.DoApp.enums.TodoItemStatus;
import To.DoApp.models.TodoItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemsRepository extends JpaRepository<TodoItemsModel, Integer> {
	Optional<TodoItemsModel> findByItemId(Integer itemId);

	Optional<TodoItemsModel> findByStatus(TodoItemStatus status);

	@Query("SELECT new com.devsoftbd.com.ToDoApp.dto.StatusCountDTO(t.status,COUNT(t.id) as c) FROM TodoItemsModel t GROUP BY t.status")
	List<StatusCountDTO> findCountGroupByStatus();
}
