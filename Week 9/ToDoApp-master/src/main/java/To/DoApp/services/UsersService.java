package To.DoApp.services;

import To.DoApp.exceptions.UsernameNotFoundException;
import To.DoApp.models.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import To.DoApp.dto.UserPrincipal;
import To.DoApp.repositories.UsersRepository;

@Service
public class UsersService implements UserDetailsService {
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(username)) {
			throw new UsernameNotFoundException("User: " + username + " was not found");
		}
		UsersModel user = usersRepository.findByUsername(username);
		if (user != null)
			return new UserPrincipal(usersRepository.findByUsername(username));
		throw new UsernameNotFoundException("User: " + username + " was not found");
	}
}
