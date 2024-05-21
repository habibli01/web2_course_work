package To.DoApp.repositories;

import To.DoApp.models.TodoItemsChangeLogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemsChangeLogRepository extends JpaRepository<TodoItemsChangeLogModel, Integer> {

}
