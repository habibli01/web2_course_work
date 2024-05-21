package To.DoApp.repositories;

import To.DoApp.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Integer> {
	public UsersModel findByUsername(String username);
}
