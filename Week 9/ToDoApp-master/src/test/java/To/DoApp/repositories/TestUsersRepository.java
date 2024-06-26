package To.DoApp.repositories;

import static org.junit.Assert.assertEquals;

import To.DoApp.models.UsersModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestUsersRepository {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UsersRepository userRepository;
	private UsersModel user;

	public void init() {
		user = new UsersModel("admin", passwordEncoder.encode("admin"), "Palash Kumar", "Nath", true);
		entityManager.persist(user);
	}

	@Test
	public void findByUsernameTest() {
		UsersModel persistedUser = userRepository.findByUsername("admin");
		assertEquals(user, persistedUser);
	}
}
