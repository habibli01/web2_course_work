package To.DoApp.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import To.DoApp.models.UsersModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal extends UsersModel implements UserDetails {

	private static final long serialVersionUID = 1L;

	public UserPrincipal(UsersModel user) {
		super(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return super.isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return super.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return super.isEnabled();
	}

	@Override
	public Integer getUserId() {
		return super.getUserId();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled();
	}
}
